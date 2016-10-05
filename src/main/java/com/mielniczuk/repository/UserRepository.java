package com.mielniczuk.repository;

import com.mielniczuk.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Pawel on 2016-10-05.
 */
public interface UserRepository extends CrudRepository<Customer, Long>{

    Customer findByName(String name);

}
