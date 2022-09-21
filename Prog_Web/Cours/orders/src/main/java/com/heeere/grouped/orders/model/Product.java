package com.heeere.grouped.orders.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Product
 */
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;
    String unit;
    float indicativePerUnit;

    boolean archived = false;

    
    public Product() {
    }

    public Product(String name, String unit, float indicativePerUnit) {
        this.name = name;
        this.unit = unit;
        this.indicativePerUnit = indicativePerUnit;
    }
}
