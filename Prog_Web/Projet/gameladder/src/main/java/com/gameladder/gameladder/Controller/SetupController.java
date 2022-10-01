package com.gameladder.gameladder.Controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gameladder.gameladder.User.User;
import com.gameladder.gameladder.User.UserService;

@Controller
@RequestMapping("/setup")          // Controller qui s'active dans le /setup en URL
public class SetupController {

    @Inject UserService userService;

    @RequestMapping("/user")       // Page (/setup/user)
    public String addAdmin(){
        /* On créer nos 4 comptes */   
        // userService.saveUserComputingDerivedPassword(new User("Shooterah"), "1234");
        // userService.saveUserComputingDerivedPassword(new User("JojoLgenius"), "1234");
        // userService.saveUserComputingDerivedPassword(new User("Godefroy"), "1234");
        // userService.saveUserComputingDerivedPassword(new User("m.art"), "1234");

        /* On les mets ADMIN */
        // userService.makeUserAdmin("Shooterah");
        // userService.makeUserAdmin("JojoLgenius");
        // userService.makeUserAdmin("Godefroy");
        // userService.makeUserAdmin("m.art");

        return "redirect:/";
    }
}
