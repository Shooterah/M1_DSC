package com.gameladder.gameladder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

    /************************************************************************************************/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // L'id se créer tout seul grâce a la JPA

    private String name;
    private String description;

    /************************************************************************************************/

    public Category() {
        super();
    }

    /**
     * 
     * @param _name
     * @param _description
     */
    public Category(String _name, String _description) {
        super();
        this.name = _name;
        this.description = _description;
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

    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", description="  + description + "]";
    }

    /************************************************************************************************/

    

    

    
}
