

package com.heeere.grouped.orders.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.heeere.grouped.orders.model.user.User;

import lombok.Data;
import lombok.ToString;

/**
 * OrderEntry
 */
@Data
@Entity
@Table(name = "allorders") // table name "order" might be already used, so we specify another name explicitly
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ToString.Exclude // to avoid circular dependency leading to stack overflow on toString
    @ManyToOne
    OrderSheet orderSheet;

    @ManyToOne
    User user;
    
    float quantity;

    @ManyToOne
    Product product;

    public Order() {
    }

    public Order(User user, float quantity, Product product) {
        this.user = user;
        this.quantity = quantity;
        this.product = product;
    }
}