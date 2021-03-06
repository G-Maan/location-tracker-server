package com.mielniczuk.repository;

import com.mielniczuk.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Pawel on 2016-10-05.
 */
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User>{

    User findByName(String name);
    User findByEmail(String email);
    Iterable<User> findByEmailContainingIgnoreCase(String email);
}
