package com.tp3.tp3.Class;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Vegetable {

    private String name;
    private String color;
    private float price;

    @Id
    @GeneratedValue
    private long id;
    
}
