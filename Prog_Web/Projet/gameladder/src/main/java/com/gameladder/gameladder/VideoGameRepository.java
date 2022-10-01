package com.gameladder.gameladder;

import org.springframework.data.repository.CrudRepository;

public interface VideoGameRepository extends CrudRepository<VideoGame, String> {

    /* Repository (Database tout ça) pour les Jeux Video */
    
}
