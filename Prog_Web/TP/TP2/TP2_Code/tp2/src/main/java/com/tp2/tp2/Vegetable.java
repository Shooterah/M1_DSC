package com.tp2.tp2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Vegetable {

    private String name;
    private String color;
    private double price;

    @Id
    @GeneratedValue
    private long id; // Pour faire une JPA entity

    /* ---------------------------------------------------------------------- */

    
    public Vegetable() { // Default Constructor
        super();
    }

    /**
     * 
     * @param name_
     * @param color_
     * @param price_
     */
    public Vegetable(String name_, String color_, double price_) { // Full Constructor
        super();
        name = name_;
        color = color_;
        price = price_;
    }

    /* ---------------------------------------------------------------------- */

    public String getName() {
        return name;
    }

    public void setName(String name_) {
        name = name_;
    }

    /* ---------------------------------------------------------------------- */

    public String getColor() {
        return color;
    }

    public void setColor(String color_) {
        color = color_;
    }

    /* ---------------------------------------------------------------------- */

    public double getPrice() {
        return price;
    }

    public void setPrice(double price_) {
        price = price_;
    }

    /* ---------------------------------------------------------------------- */

    public long getId() {
        return id;
    }

    public void setId(long id_) {
        id = id_;
    }

    /* ---------------------------------------------------------------------- */

}
