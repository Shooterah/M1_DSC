package com.tp2.tp2;

public class Vegetable {

    private String name;
    private String color;
    private double price;

    /* ---------------------------------------------------------------------- */

    public Vegetable(){ // Default Constructor
        super();
    }

    public Vegetable(String name_, String color_, double price_){ // Full Constructor
        super();
        name = name_;
        color = color_;
        price = price_;
    }

    /* ---------------------------------------------------------------------- */

    public String getName(){
        return name;
    }

    public void setName(String name_){
        name = name_;
    }

    /* ---------------------------------------------------------------------- */

    public String getColor(){
        return color;
    }

    public void setColor(String color_){
        color = color_;
    }

    /* ---------------------------------------------------------------------- */

    public double getPrice(){
        return price;
    }

    public void setPrice(double price_){
        price = price_;
    }

    /* ---------------------------------------------------------------------- */

}
