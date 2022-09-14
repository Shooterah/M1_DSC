package com.heeere.grouped.orders.model.repos;

import java.util.List;

import com.heeere.grouped.orders.model.OrderSheet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * OrderSheetRepository
 */
public interface OrderSheetRepository extends CrudRepository<OrderSheet, Long> {

    /* ___JPQL___ */
    @Query("SELECT os FROM OrderSheet os WHERE (SELECT count(u) FROM User u, UserGroup g WHERE u.userName = :name AND g MEMBER of os.userGroups AND u MEMBER OF g.users) > 0 ")
	List<OrderSheet> forUser(String name);

}