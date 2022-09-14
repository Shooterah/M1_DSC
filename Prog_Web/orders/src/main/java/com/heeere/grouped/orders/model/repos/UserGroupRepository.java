package com.heeere.grouped.orders.model.repos;

import java.util.List;

import com.heeere.grouped.orders.model.UserGroup;
import com.heeere.grouped.orders.model.user.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * UserGroupRepository
 */
public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

    /* ___JPQL___ */
    @Query("SELECT g FROM UserGroup g WHERE :u MEMBER OF g.users")
    List<UserGroup> findAllByUser(User u);

}