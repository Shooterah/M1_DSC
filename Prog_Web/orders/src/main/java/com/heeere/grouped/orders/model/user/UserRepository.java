
package com.heeere.grouped.orders.model.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 */
public interface UserRepository extends CrudRepository<User, String> {

    public List<User> findAllByOrderByUserName();
    
}
