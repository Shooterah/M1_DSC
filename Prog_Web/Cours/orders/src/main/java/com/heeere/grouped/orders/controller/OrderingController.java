package com.heeere.grouped.orders.controller;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.heeere.grouped.orders.model.Order;
import com.heeere.grouped.orders.model.OrderSheet;
import com.heeere.grouped.orders.model.Product;
import com.heeere.grouped.orders.model.QOrderSheet;
import com.heeere.grouped.orders.model.UserGroup;
import com.heeere.grouped.orders.model.repos.OrderRepository;
import com.heeere.grouped.orders.model.repos.OrderSheetRepository;
import com.heeere.grouped.orders.model.repos.ProductRepository;
import com.heeere.grouped.orders.model.user.QUser;
import com.heeere.grouped.orders.model.user.User;
import com.heeere.grouped.orders.model.user.UserRepository;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * OrderingController
 */
@Controller
@RequestMapping("/ordering")
public class OrderingController {

    @Inject
    OrderSheetRepository orderSheetRepository;
    @Inject
    OrderRepository orderRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    ProductRepository productRepository;

    @RequestMapping("") // empty, to catch http://localhost:8080/ordering 
    public String root() {
        return "redirect:ordering/";
    }

    @RequestMapping("/")
    public String rootSlash(Model model, Authentication auth) {
        model.addAttribute("sheets", orderSheetRepository.forUser(auth.getName()));
        return "list-order-sheets";
    }

    /* ___QUERYDSL___ */
    @PersistenceContext
    EntityManager entityManager;
    /* ___QUERYDSL___ */
    @RequestMapping("/querydsl")
    public String rootSlashQuerydsl(Model model, Authentication auth) {
        QOrderSheet orderSheet = QOrderSheet.orderSheet;
        var query = new JPAQuery<OrderSheet>(entityManager);
        // version with two queries
        //User u = userRepository.findById(auth.getName()).get(); // first query..., maybe can do a better query below to avoid it
        //var orderSheets = query.from(orderSheet).where(orderSheet.userGroups.any().users.contains(u)).fetch();
        // version in a "single" query
        QUser user = QUser.user;
        var orderSheets = query.from(orderSheet).where(
            orderSheet.userGroups.any().users.contains(
              JPAExpressions.selectFrom(user).where(user.userName.eq(auth.getName()))
            )).fetch();
        model.addAttribute("sheets", orderSheets);
        return "list-order-sheets";
    }

    
    @RequestMapping("/{id}")
    public String editSheet(Model model, @ModelAttribute("id") OrderSheet sh, Authentication auth) {
        User u = userRepository.findById(auth.getName()).get();
        boolean ok = false;
        for (UserGroup g : sh.getUserGroups()) {
            if (g.getUsers().contains(u)) {
                ok = true;
            }
        }
        if (!ok) throw new IllegalAccessError();
        String currentUserName = u.getUserName();
        User currentUser = userRepository.findById(currentUserName).get();
        model.addAttribute("currentUserName", currentUserName);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("sh", sh);

        model.addAttribute("joinOrderQuantities", new Object() {
            @SuppressWarnings("unused") /* the method will be used by the template, not java code */
            public String apply(List<Order> l, String sep) {
                if (l == null) return "";
                return l.stream().map(o -> ""+o.getQuantity()).collect(Collectors.joining(sep));
            }
        });

        Predicate<Order> filter = sh.isVisibleToAll() ? o -> true : o -> o.getUser().getUserName().equals(currentUserName);
        var orders = sh.getOrders().stream().filter(filter).collect(Collectors.groupingBy(Order::getUser, Collectors.groupingBy(Order::getProduct)));
        orders.computeIfAbsent(currentUser, k -> new HashMap<>());
        var estimatedUserBill = sh.getOrders().stream().filter(filter).collect(Collectors.groupingBy(Order::getUser, Collectors.summingDouble(o -> o.getQuantity()*o.getProduct().getIndicativePerUnit())));
        model.addAttribute("orders", orders);
        model.addAttribute("distinctProducts", sh.getProductGroups().stream().flatMap(g -> g.getProducts().stream()).distinct().collect(Collectors.toList()));
        model.addAttribute("estimatedUserBill", estimatedUserBill);

        return "order-in-sheet";
    }

    @RequestMapping(value="saveForUser", method=RequestMethod.POST)
    public String saveForUser(@RequestParam long sheetId, @RequestParam String userName, @RequestParam List<Long> pid, @RequestParam List<String> val, Authentication auth) {
        if (! auth.getName().equals(userName)) throw new IllegalAccessError("Cheat?");

        User user = userRepository.findById(userName).get();
        OrderSheet sh = orderSheetRepository.findById(sheetId).get();
        
        List<Order> toRemove = orderRepository.forUser(sheetId, user);
        sh.getOrders().removeAll(toRemove);
        for (int i = 0; i < pid.size(); i++) {
            String v = val.get(i);
            if (v.isEmpty()) continue;
            Product product = productRepository.findById(pid.get(i)).get();
            String[] parts = v.split(" +");
            for (String p : parts) {
                Order o = new Order(user, Float.parseFloat(p), product);
                o.setOrderSheet(sh);
                orderRepository.save(o);
                sh.getOrders().add(o);
            }
        }
        orderSheetRepository.save(sh);

        orderRepository.deleteAll(toRemove);
        return "redirect:./" + sheetId;
    }
    

}