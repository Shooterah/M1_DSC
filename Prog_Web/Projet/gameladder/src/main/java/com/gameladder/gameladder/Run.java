package com.gameladder.gameladder;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Run {

    /************************************************************************************************/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // L'id se créer tout seul grâce a la JPA

    private long idUser;
    private long idGame;
    private long idCategory;
    private Date runDate;
    private String time; // String output = String.format("%02d:%02d:%02d", hours , minutes , seconds ) ;
    
    /************************************************************************************************/
    
    public Run() {
        super();
    }

    /**
     * 
     * @param idUser
     * @param idGame
     * @param idCategory
     * @param time
     */
    public Run(long idUser, long idGame, long idCategory, String time) {
        this.idUser = idUser;
        this.idGame = idGame;
        this.idCategory = idCategory;
        this.time = time;
        this.runDate = new java.sql.Date(System.currentTimeMillis());
    }

    /************************************************************************************************/

    public long getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    /**
     * 
     * @param idUser
     */
    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

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

    public long getIdCategory() {
        return idCategory;
    }

    /**
     * 
     * @param idCategory
     */
    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public Date getRunDate() {
        return runDate;
    }

    /**
     * 
     * @param runDate
     */
    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }

    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Run [id=" + id + ", idUser=" + idUser + ", idGame=" + idGame + ", idCategory=" + idCategory +  ", runDate=" + runDate + ", time=" + time + "]";
    }

}
