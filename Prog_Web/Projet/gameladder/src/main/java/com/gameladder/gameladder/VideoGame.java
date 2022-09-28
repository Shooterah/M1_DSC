package com.gameladder.gameladder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VideoGame {

    /************************************************************************************************/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // L'id se créer tout seul grâce a la JPA

    private String name;
    private String description;
    private long nbrRun;

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

    @Override
    public String toString() {
        return "VideoGame [id=" + id + ", name=" + name + ", description=" + description + ", nbrRun=" + nbrRun + "]";
    }
    
}
