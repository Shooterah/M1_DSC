package com.heeere.grouped.orders.controller;

import javax.inject.Inject;

import com.heeere.grouped.orders.AddTestData;
import com.heeere.grouped.orders.model.repos.OrderSheetRepository;
import com.heeere.grouped.orders.model.user.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SetupController
 */
@Controller
@RequestMapping("/SETUP") // RequestMapping in the methods (below), will be relative to /SETUP
public class SetupController {

    @Inject
    UserService userService; // This will be set automatically (injection) by spring data, with an
                             // implementation of UserService
    @Inject
    OrderSheetRepository OrderSheetRepository;

    @Inject
    AddTestData addDataService; // This will get injected (and the AddTestData that will get instantiated will
                                // also have its own fields properly injected; it would not be the case with new
                                // AddTestData())

    @RequestMapping("/user") // This will handle /SETUP/user
    public String addAdmin() {
        // userService.saveUserComputingDerivedPassword(new User("abby"), "cd");
        // userService.makeUserAdmin("abby");
        return "redirect:/";
    }

    @RequestMapping("/data")
    public String addData() {
        /* ___GENERATETESTDATA___ */
        addDataService.generateTestData();
        return "redirect:/";
    }

}