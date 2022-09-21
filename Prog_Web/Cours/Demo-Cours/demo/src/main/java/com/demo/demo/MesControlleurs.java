package com.demo.demo;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Anotation (Magie)
public class MesControlleurs {

    List<Personne> lesGens = new ArrayList<>(); // Liste de Personne

    public MesControlleurs() { // Constructeur, on ajoute 2 personne dans la liste
        lesGens.add(new Personne("Florian", "Doffemont", 22));
        lesGens.add(new Personne("Jeoffrey", "Pereira", 23));
    }

    @GetMapping("/") // Gere que les requêtes GET, Je gère / (La racine)
    /* @ResponseBody // Renvois de la méthode = réponse HTTP */
    public String laPageDAcceuil(Model model) { // Génere la page HTML de la liste des Gens

        model.addAttribute("qui", "Flof"); // Ca envois les attribut du model a la template
        model.addAttribute("titre", "Accueil"); // Ce sont des controlleurs
        model.addAttribute("gens", lesGens);
        return "maison"; // Ca return un Template
    }

    @GetMapping("/gens/{identifiant}")
    public String detailsDuGens(Model model, @PathVariable long identifiant) { // Genere la page HTML du détails de la
                                                                               // Personne
        var p = lesGens.stream().filter(o -> o.id == identifiant).findFirst().get(); // Liste des gens qui ont
                                                                                     // l'identifiant 1 et on prend le
                                                                                     // premier
        model.addAttribute("p", p);
        return "details";
    }

    @GetMapping("/gens/{identifiant}/rm")
    public String suppr(Model model, @PathVariable long identifiant) { // Genere la page HTML qui supprime la personne
                                                                       // avec un certain identifiant
        lesGens.removeIf(bidule -> bidule.id == identifiant);
        return "redirect:/";
    }

    @GetMapping("/gens/new")
    public String ajout(Personne p) {
        lesGens.add(p);
        return "redirect:/";
    }

    @GetMapping("/gens/newALT")
    public String ajout(@RequestParam String nom, @RequestParam String prenom, @RequestParam int age) {
        lesGens.add(new Personne(nom, prenom, age));
        return "redirect:/";
    }
}