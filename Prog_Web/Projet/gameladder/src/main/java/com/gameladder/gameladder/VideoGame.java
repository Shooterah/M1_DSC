package com.gameladder.gameladder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gameladder.gameladder.User.User;

import lombok.Data;

@Data
@Entity 
@Table(name="T_Game")
public class VideoGame {

    /************************************************************************************************/

    @Id
    private String name;  // Clé primaire de la  table T_Game

    private String description;
    private long nbrRun;

    // Liste des utilisateurs qui ont joué a ce jeu vidéo
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "T_User_Game",                        // Permet de créer la relation
               joinColumns = @JoinColumn(name = "name"),      // Jeux - User dans la BDD
               inverseJoinColumns = @JoinColumn(name = "userName"))
    private List<User> users = new ArrayList<>(); 

    /************************************************************************************************/

    public VideoGame() {
        super();
    }

    /**
     * 
     * @param _name
     * @param _description
     */
    public VideoGame(String _name, String _description) {
        super();
        this.name = _name;
        this.description = _description;
        this.nbrRun = 0;
    }

    /************************************************************************************************/

    
}
