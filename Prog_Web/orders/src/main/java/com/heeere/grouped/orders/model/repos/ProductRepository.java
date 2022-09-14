package com.heeere.grouped.orders.model.repos;

import com.heeere.grouped.orders.model.Product;

import org.springframework.data.repository.CrudRepository;

/**
 * ProductRepository
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    
}