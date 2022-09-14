package com.heeere.grouped.orders.model.repos;

import java.util.List;

import com.heeere.grouped.orders.model.Order;
import com.heeere.grouped.orders.model.user.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * OrderRepository
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

    /* ___JPQL___ */
    @Query("SELECT o FROM Order o, OrderSheet os WHERE os.id = :sheetId AND o.id MEMBER OF os.orders AND o.user = :user")
	List<Order> forUser(long sheetId, User user);


    List<Order> findAllByUser(User u);
}