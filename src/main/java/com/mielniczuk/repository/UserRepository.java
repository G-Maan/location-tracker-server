package com.mielniczuk.repository;

import com.mielniczuk.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Pawel on 2016-10-05.
 */
public interface UserRepository extends CrudRepository<User, Long>{

    User findByName(String name);

}
