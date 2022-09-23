package com.tp2.tp2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.inject.Inject;

@Controller
public class FirstController {

    @Inject VegetableRep veRep;

    @RequestMapping
    public String simplePage(Model model){
        model.addAttribute("hi", "hellowwww");
        return "simple";
    }
    
}
