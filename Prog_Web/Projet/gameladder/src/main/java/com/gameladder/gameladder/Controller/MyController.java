package com.gameladder.gameladder.Controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gameladder.gameladder.CategoryRepository;
import com.gameladder.gameladder.RunRepository;
import com.gameladder.gameladder.VideoGameRepository;
import com.gameladder.gameladder.User.UserRepository;

@Controller
public class MyController {

    @Inject UserRepository userRep;             // On inject les données des Utilisateurs
    @Inject VideoGameRepository gameRep;        // On inject les données des Jeux Video
    @Inject CategoryRepository categoryRep;     // On inject les données des Category
    @Inject RunRepository runRep;               // On inject les données des Runs

    @RequestMapping(value = "/")
    public String homePage(Model model){
        
        
        model.addAttribute("users", userRep.findAll());               // On met dans le model les données Utilisateurs
        model.addAttribute("games", gameRep.findAll());               // On met dans le model les données Jeux Video
        model.addAttribute("categorys", categoryRep.findAll());       // On met dans le model les données Category
        model.addAttribute("runs", runRep.findAll());                 // On met dans le model les données Runs
        return "home";
    }
    
}
