package com.heeere.grouped.orders.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.heeere.grouped.orders.model.user.User;

import lombok.Data;

/**
 * Group
 */
@Data
@Entity
public class UserGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;
    boolean archived = false;

    @ManyToMany
    List<User> users = new ArrayList<>();

    public UserGroup() {
    }
    public UserGroup(String name) {
        this.name = name;
    }
}