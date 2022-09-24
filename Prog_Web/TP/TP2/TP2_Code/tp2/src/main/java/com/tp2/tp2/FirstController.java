package com.tp2.tp2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
public class FirstController {

    @Inject VegetableRepository veRep;

    /**
     * 
     * @param model
     * @return 
     */
    @RequestMapping(value = "/")                    // Affiche la page 'racine'
    public String simplePage(Model model){
        model.addAttribute("hi", "hellowwww");      // On ajoute au model l'attribut 'hi' et sa valeur
        model.addAttribute("veges", veRep);         // On ajoute au model l'attribut 'veges' qui a la liste de légume
        model.addAttribute("veg", new Vegetable()); // On ajoute au model un légume vide pour l'ajouter avec un form
        return "simple";                            // On affiche la page 'simple' en envoyant tout les attributs
    }

    /**
     * 
     * @param model
     * @param vege
     * @return
     */
    @RequestMapping(value="/addvegetable", method = RequestMethod.POST) // Affiche la page 'addvegetable'
    public String addVegetable(Model model, Vegetable vege){
        veRep.save(vege);                                               // On save dans la liste le légume qu'on a reçu
        return "redirect:/";                                            // On affiche la page 'racine'
    }
    
}
