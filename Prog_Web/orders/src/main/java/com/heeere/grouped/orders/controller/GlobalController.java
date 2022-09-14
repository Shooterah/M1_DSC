package com.heeere.grouped.orders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * GlobalController
 */
@Controller
public class GlobalController {

    @RequestMapping("") // empty, to catch http://localhost:8080/    
    public String root() {
        return "redirect:/ordering";
    }
    
}