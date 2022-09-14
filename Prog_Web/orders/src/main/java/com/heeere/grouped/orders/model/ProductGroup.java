

package com.heeere.grouped.orders.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

/**
 * ProductGroup
 */
@Data
@Entity
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;
    boolean archived = false;

    @ManyToMany
    List<Product> products = new ArrayList<>();

    public ProductGroup() {
    }

    public ProductGroup(String name) {
        this.name = name;
    }
}
