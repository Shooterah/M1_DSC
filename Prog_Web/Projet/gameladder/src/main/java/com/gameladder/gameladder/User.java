package com.gameladder.gameladder;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    /************************************************************************************************/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // L'id se créer tout seul grâce a la JPA

    private String username;
    private String password;
    private Date signIn;
    private ArrayList<VideoGame> playedGames; // Liste des jeux-vidéo auquel a joué l'utilisateur

    /************************************************************************************************/

    public User() {
        super();
        this.playedGames = new ArrayList<VideoGame>();
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
        this.playedGames = new ArrayList<VideoGame>();
    }

    /************************************************************************************************/

    public long getId() {
        return id;
    }

    /**
     * 
     * @param _id
     */
    public void setId(long _id) {
        this.id = _id;
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

    public ArrayList<VideoGame> getPlayedGames() {
        return playedGames;
    }

    /**
     * 
     * @param _playedGames
     */
    public void setPlayedGames(ArrayList<VideoGame> _playedGames) {
        this.playedGames = _playedGames;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", signIn=" + signIn
                + ", playedGames=" + playedGames.toString() + "]";
    }
    
}
