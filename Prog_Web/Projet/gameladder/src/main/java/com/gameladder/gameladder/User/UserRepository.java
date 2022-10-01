package com.gameladder.gameladder.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    /* Repository (Database tout ça) pour les Utilisateurs */

    public List<User> findAllByOrderByUserName(); // Requete SQL
    
}
