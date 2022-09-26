package com.tp2.tp2;

import org.springframework.data.repository.CrudRepository;


public interface VegetableRepository extends CrudRepository<Vegetable, Long> {
    
    /* Juste cette interface remplace la classe VegetableRep et l'interface TempVegetableRep
     * car cette interface est 'extends' de 'CrudRepository qui fait tout tout seul !'
     */

}