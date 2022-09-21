package com.heeere.grouped.orders.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * Order
 */
@Data
@Entity
public class OrderSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;
    boolean archived = false;
    boolean visibleToAll = true;

    @Column(name = "orderDate") // date is a reserved keyword in SQL so the default field name would cause an error, we have to specify the name explicitly
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Order> orders = new ArrayList<>();

    @ManyToMany
    List<ProductGroup> productGroups = new ArrayList<>();

    @ManyToMany
    List<UserGroup> userGroups = new ArrayList<>();

    public OrderSheet() {
    }

    public OrderSheet(String name) {
        this.name = name;
        this.date = new Date();
    }

}