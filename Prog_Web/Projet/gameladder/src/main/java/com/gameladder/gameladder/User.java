package com.gameladder.gameladder;

import java.sql.Date;
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

@Entity @Table(name = "Users")
public class User {

    /************************************************************************************************/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser; // L'id se créer tout seul grâce a la JPA

    private String username;
    private String password;
    private Date signIn;
    
    // Liste des jeux-vidéo auquel a joué l'utilisateur
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "T_User_Game",                            // Permet de créer la relation
               joinColumns = @JoinColumn(name = "idUser"),          // Jeux - User dans la BDD 
               inverseJoinColumns = @JoinColumn(name = "idGame"))
    private List<VideoGame> playedGames = new ArrayList<>(); 

    /************************************************************************************************/

    public User() {
        super();
        this.signIn = new java.sql.Date(System.currentTimeMillis());
    }

    /**
     * 
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.signIn = new java.sql.Date(System.currentTimeMillis());
    }

    /************************************************************************************************/

    public long getId() {
        return idUser;
    }

    /**
     * 
     * @param _id
     */
    public void setId(long _id) {
        this.idUser = _id;
    }

    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param _username
     */
    public void setUsername(String _username) {
        this.username = _username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param _password
     */
    public void setPassword(String _password) {
        this.password = _password;
    }

    public Date getSignIn() {
        return signIn;
    }

    /**
     * 
     * @param _signIn
     */
    public void setSignIn(Date _signIn) {
        this.signIn = _signIn;
    }

    public List<VideoGame> getPlayedGames() {
        return playedGames;
    }

    /**
     * 
     * @param _playedGames
     */
    public void setPlayedGames(List<VideoGame> _playedGames) {
        this.playedGames = _playedGames;
    }

    public void addGame(VideoGame vg){
        this.playedGames.add(vg);
    }

    @Override
    public String toString() {
        return "User [idUser=" + idUser + ", username=" + username + ", password=" + password + ", signIn=" + signIn
                + ", playedGames=" + playedGames.toString() + "]";
    }
    
}
