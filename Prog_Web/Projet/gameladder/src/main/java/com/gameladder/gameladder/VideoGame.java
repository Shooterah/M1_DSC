package com.gameladder.gameladder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity @Table(name="T_Game")
public class VideoGame {

    /************************************************************************************************/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idGame; // L'id se créer tout seul grâce a la JPA

    private String name;
    private String description;
    private long nbrRun;

    // Liste des jeux-vidéo auquel a joué l'utilisateur
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "T_User_Game",                        // Permet de créer la relation
               joinColumns = @JoinColumn(name = "idGame"),      // Jeux - User dans la BDD
               inverseJoinColumns = @JoinColumn(name = "idUser"))
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

    
    public long getIdGame() {
        return idGame;
    }

    /**
     * 
     * @param idGame
     */
    public void setIdGame(long idGame) {
        this.idGame = idGame;
    }

    public String getName() {
        return name;
    }

    /**
     * 
     * @param _name
     */
    public void setName(String _name) {
        this.name = _name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param _description
     */
    public void setDescription(String _description) {
        this.description = _description;
    }

    public long getNbrRun() {
        return nbrRun;
    }

    /**
     * 
     * @param _nbrRun
     */
    public void setNbrRun(long _nbrRun) {
        this.nbrRun = _nbrRun;
    }

    public List<User> getUsers() {
        return users;
    }

    /**
     * 
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "VideoGame [idGame=" + idGame + ", name=" + name + ", description=" + description + ", nbrRun=" + nbrRun + "]";
    }
    
}
