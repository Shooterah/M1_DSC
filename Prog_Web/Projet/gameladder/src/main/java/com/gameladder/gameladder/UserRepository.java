package com.gameladder.gameladder;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    /* Repository (Database tout ça) pour les Utilisateurs */
    
}
