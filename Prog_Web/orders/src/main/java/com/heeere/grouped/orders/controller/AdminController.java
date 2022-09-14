package com.heeere.grouped.orders.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;

import com.heeere.grouped.orders.model.repos.OrderRepository;
import com.heeere.grouped.orders.model.repos.OrderSheetRepository;
import com.heeere.grouped.orders.model.repos.UserGroupRepository;
import com.heeere.grouped.orders.model.user.User;
import com.heeere.grouped.orders.model.user.UserRepository;
import com.heeere.grouped.orders.model.user.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * AdminController
 */
@Controller
@RequestMapping("/admin") // RequestMapping in the methods (below), will be relative to /admin
public class AdminController {

    @Inject
    UserService userService;
    @Inject
    UserRepository userRepository;
    @Inject
    OrderRepository orderRepository;
    @Inject
    OrderSheetRepository orderSheetRepository;
    @Inject
    UserGroupRepository userGroupRepository;

    @RequestMapping("/reset-password/{uid}")
    public String resetPass(@RequestParam String newPassword, @ModelAttribute("uid") User u, @RequestParam(defaultValue = "") String moreBody, @RequestParam(defaultValue = "") String moreSubject)
            throws UnsupportedEncodingException {
        userService.saveUserComputingDerivedPassword(u, newPassword);
        String subject = "[order] user/password" + moreSubject;
        String email = "TTTTODOOOO@DOMAIN.NAME";
        String body = "";
        body += "user: "+u.getUserName()+"\n";
        body += "pass: "+newPassword+"\n";
        body += moreBody;
        if (u.getUserName().contains("@")) {
            email = u.getUserName();
        } else if (u.getDisplayName().contains("@")) {
            email = u.getDisplayName().replaceAll("^([^@]* )*([^ ]*)( [^@]*)*$", "$2");
        }
        return "redirect:mailto:"+email+"?subject="+escape(subject)+"&body="+escape(body);
    }
    public String escape(String v) throws UnsupportedEncodingException {
        return URLEncoder.encode(v, "UTF-8").replace("+", "%20");
    }

    @RequestMapping("/delete-user/{uid}/confirm")
    public String deleteUserConfirm(@ModelAttribute("uid") User u, Model model) {
        var orders = orderRepository.findAllByUser(u);
        var groups = userGroupRepository.findAllByUser(u);
        model.addAttribute("message",
        "Really delete user " + u.getUserName() +
        " with " + orders.size() + " orders" +
        " and " + groups.size() + " groups"
        );
        model.addAttribute("target", ".");
        return "confirm";
    }
    @RequestMapping("/delete-user/{uid}")
    public String deleteUser(@ModelAttribute("uid") User u) {
        var orders = orderRepository.findAllByUser(u);
        var groups = userGroupRepository.findAllByUser(u);
        orders.forEach(o -> {
            var os = o.getOrderSheet();
            System.err.println("#" + os);
            os.getOrders().remove(o);
            orderSheetRepository.save(os);
        });
        orderRepository.deleteAll(orders);
        groups.forEach(g -> g.getUsers().remove(u));
        userGroupRepository.saveAll(groups);
        userRepository.delete(u);
        return "redirect:/manage/all";
    }
    @RequestMapping("/mass-add-users")
    public String massAddUsers(@RequestParam String allUsers) {
        var lines = allUsers.split("\n");
        for (String l : lines) {
            l = l.strip();
            if (l.length() == 0) continue;
            String id = l;
            String displayName = l;
            if (l.contains("<") && l.contains(">")) {
                displayName= l.replaceAll("<.*", "");
                id = l.substring(displayName.length()+1).replaceAll(">.*", "");
                displayName = displayName.strip();
                id = id.strip();
            }
            if (userRepository.existsById(id)) {
                System.out.println("#### skipping existing user ["+id+"]");
                continue;
            }
            User u = new User(id);
            u.setDisplayName(displayName);
            u.setDerivedPassword("IMPOSSIBLE");
            userRepository.save(u);
        }
        return "redirect:/manage/all";
    }

}