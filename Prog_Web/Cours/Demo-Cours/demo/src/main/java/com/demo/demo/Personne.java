package com.demo.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Permet d'ajouter plein de méthodes tout seul
@AllArgsConstructor
public class Personne {

    long id;
    static long nextId = 0;

    public Personne() {
        this.id = nextId++;
    }

    public Personne(String nom, String prenom, int age) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    String nom;
    String prenom;
    int age;

}
