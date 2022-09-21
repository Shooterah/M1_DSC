package com.heeere.grouped.orders.controller;

import java.util.Random;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.heeere.grouped.orders.formdata.UserPartial;
import com.heeere.grouped.orders.model.Order;
import com.heeere.grouped.orders.model.OrderSheet;
import com.heeere.grouped.orders.model.Product;
import com.heeere.grouped.orders.model.ProductGroup;
import com.heeere.grouped.orders.model.UserGroup;
import com.heeere.grouped.orders.model.repos.OrderSheetRepository;
import com.heeere.grouped.orders.model.repos.ProductGroupRepository;
import com.heeere.grouped.orders.model.repos.ProductRepository;
import com.heeere.grouped.orders.model.repos.UserGroupRepository;
import com.heeere.grouped.orders.model.user.User;
import com.heeere.grouped.orders.model.user.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * ManagerController
 */
@Controller
@RequestMapping("/manage") // RequestMapping in the methods (below), will be relative to /manage
public class ManagerController {

    @Inject
    OrderSheetRepository sheetRepo;
    @Inject
    UserRepository userRepo;
    @Inject
    ProductRepository productRepo;
    @Inject
    UserGroupRepository userGroupRepo;
    @Inject
    ProductGroupRepository productGroupRepo;

    @RequestMapping(value="/all")
    public String allSheets(Model model) {
        model.addAttribute("sheets", sheetRepo.findAll());
        model.addAttribute("productGroups", productGroupRepo.findAll());
        model.addAttribute("userGroups", userGroupRepo.findAll());
        model.addAttribute("products", productRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("newProductGroup", new ProductGroup("newpgroup"));
        model.addAttribute("newUserGroup", new UserGroup("newugroup"));
        model.addAttribute("newProduct", new Product("newprod", "kg", 999));
        model.addAttribute("newUser", new User("newuser"));
        model.addAttribute("newOrderSheet", new OrderSheet("commande"));
        return "list-all";
    }

    @RequestMapping(value="/sheet/{id}", method = RequestMethod.GET)
    public String sheetDetail(Model model, @ModelAttribute("id") OrderSheet sh) {
        model.addAttribute("sh", sh);
        model.addAttribute("allUserGroups", userGroupRepo.findAll());
        model.addAttribute("allProductGroups", productGroupRepo.findAll());
        model.addAttribute("orders", sh.getOrders().stream().collect(Collectors.groupingBy(Order::getUser, Collectors.groupingBy(Order::getProduct))));
        model.addAttribute("distinctProducts", sh.getProductGroups().stream().flatMap(g -> g.getProducts().stream()).distinct().collect(Collectors.toList()));
        var estimatedUserBill = sh.getOrders().stream().collect(Collectors.groupingBy(Order::getUser, Collectors.summingDouble(o -> o.getQuantity()*o.getProduct().getIndicativePerUnit())));
        model.addAttribute("estimatedUserBill", estimatedUserBill);

        return "sheet-details";
    }

    @RequestMapping(value="/sheet/{id}", method = RequestMethod.POST)
    public String sheetDetailSave(@ModelAttribute("id") OrderSheet sh) {
        sheetRepo.save(sh);
        return "redirect:"+sh.getId();
        //return "redirect:../all";
    }

    @RequestMapping(value="/sheet/", method = RequestMethod.POST)
    public String sheetDetailSaveNew(OrderSheet sh) {
        sheetRepo.save(sh);
        return "redirect:"+sh.getId();
        //return "redirect:../all";
    }



    /* ___FETCHOBJECT___ */
    @RequestMapping(value="/user-group/{id}", method = RequestMethod.GET)
    public String userGroupDetail(Model model, @ModelAttribute("id") UserGroup g) {
        model.addAttribute("g", g);
        model.addAttribute("allUsers", userRepo.findAll());
        return "user-group-details";
    }

    /* ___PATCHOBJECT___ */
    @RequestMapping(value="/user-group/{id}", method = RequestMethod.POST)
    public String userGroupDetailSave(@ModelAttribute("id") UserGroup g) {
        userGroupRepo.save(g);
        return "redirect:../all";
    }

    @RequestMapping(value="/user-group/", method = RequestMethod.POST)
    public String userGroupDetailSaveNew(UserGroup g) {
        userGroupRepo.save(g);
        return "redirect:../all";
    }



    @RequestMapping(value="/product-group/{id}", method = RequestMethod.GET)
    public String productGroupDetail(Model model, @ModelAttribute("id") ProductGroup g) {
        model.addAttribute("g", g);
        model.addAttribute("allProducts", productRepo.findAll());
        return "product-group-details";
    }

    @RequestMapping(value="/product-group/{id}", method = RequestMethod.POST)
    public String productGroupDetailSave(@ModelAttribute("id") ProductGroup g) {
        productGroupRepo.save(g);
        return "redirect:../all";
    }

    @RequestMapping(value="/product-group/", method = RequestMethod.POST)
    public String productGroupDetailSaveNew(ProductGroup g) {
        productGroupRepo.save(g);
        return "redirect:../all";
    }


    @RequestMapping(value="/product/{id}", method = RequestMethod.GET)
    public String productDetail(Model model, @ModelAttribute("id") Product p) {
        model.addAttribute("p", p);
        return "product-details";
    }

    @RequestMapping(value="/product/{id}", method = RequestMethod.POST)
    public String productDetailSave(@ModelAttribute("id") Product p) {
        productRepo.save(p);
        return "redirect:../all";
    }

    @RequestMapping(value="/product/", method = RequestMethod.POST)
    public String productDetailSaveNew(Product p) {
        productRepo.save(p);
        return "redirect:../all";
    }


    Random random = new Random();
    public String randomPassword(int len) {
        String res = "";
        for (int i = 0; i < len; i++) {
            res += String.format("%x", random.nextInt(16));
        }
        return res;
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    public String userDetail(Model model, @ModelAttribute("id") User u) {
        model.addAttribute("u", u);
        model.addAttribute("newPassword", randomPassword(6));
        return "user-details";
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.POST)
    public String userDetailSave(@ModelAttribute("id") User u) {
        userRepo.save(u);
        return "redirect:../all";
    }

    @RequestMapping(value="/user/", method = RequestMethod.POST)
    public String userDetailSaveNew(User u) {
        userRepo.save(u);
        return "redirect:../all";
    }

    /* ___PREVENTFIELDADDITION___ */
    @RequestMapping(value="/user/SAFE", method = RequestMethod.POST)
    public String userDetailSave(UserPartial partial) {
        User u = userRepo.findById(partial.getUserName()).get();
        BeanUtils.copyProperties(partial, u);
        userRepo.save(u);
        return "redirect:../all";
    }

}