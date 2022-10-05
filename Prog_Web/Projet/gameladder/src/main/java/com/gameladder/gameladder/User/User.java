package com.gameladder.gameladder.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gameladder.gameladder.VideoGame;

import lombok.Data;

@Data
@Entity
@Table(name = "Users") // On dis que la class User sa table dans la DB est Users
public class User {

    /************************************************************************************************/

    @Id
    private String userName; // L'username sera la clé primaire de la table Users

    private String derivedPassword; // MDP crypté de l'utilisateur
    private Date signIn; // Date d'inscription

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>(); // L'utilisateur aura un role (Pour la sécurité tout ça)

    // Liste des jeux-vidéo auquel a joué l'utilisateur
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "T_User_Game", // Permet de créer la relation
            joinColumns = @JoinColumn(name = "userName"), // Jeux - User dans la BDD
            inverseJoinColumns = @JoinColumn(name = "idGame"))
    private List<VideoGame> playedGames = new ArrayList<>();

    /************************************************************************************************/

    public User() {
        super();
        this.signIn = new java.sql.Date(System.currentTimeMillis()); // Date de maintenant
    }

    /**
     * 
     * @param userName
     */
    public User(String userName) {
        this.userName = userName;
        this.roles.add(UserRole.USER); // Simple utilisateur lors de l'inscription
        this.derivedPassword = null; // Pas de MDP par défaut
        this.signIn = new java.sql.Date(System.currentTimeMillis()); // Date de maintenant
    }

    public User(String userName, String derivedPassword, List<String> roles) {
        this.userName = userName;
        this.derivedPassword = derivedPassword;
        // Ajoute les roles de la liste
        this.roles.addAll(roles.stream().map(UserRole::valueOf).collect(Collectors.toList()));
        this.signIn = new java.sql.Date(System.currentTimeMillis()); // Date de maintenant
    }

    /************************************************************************************************/

}
