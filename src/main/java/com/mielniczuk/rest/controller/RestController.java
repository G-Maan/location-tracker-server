package com.mielniczuk.rest.controller;

import com.mielniczuk.model.User;
import com.mielniczuk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;


/**
 * Created by Pawel on 2016-10-03.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/print/{name}", method = RequestMethod.GET)
    public void printCustomer(@PathVariable("name") final String name){
        User customer = userRepository.findByName(name);
        System.out.println(customer.getId() + " " + customer.getName() + " " + customer.getEmail());
    }

    @RequestMapping(value = "/save/{name}/{email}", method = RequestMethod.GET)
    public void saveNewCustomer(@PathVariable("name") final String name, @PathVariable("email") final String email){
        User newCustomer = new User(name, email);
        userRepository.save(newCustomer);
    }

    @RequestMapping(value = "/save/random/{id}", method = RequestMethod.GET)
    public void updateCustomerLocation(@PathVariable("id") final Long id){
        Random rand = new Random();
        double latitude = rand.nextDouble();
        double longitude = rand.nextDouble();
        User customer = userRepository.findOne(id);
        customer.setLatitude(latitude);
        customer.setLongitude(longitude);
        userRepository.save(customer);
    }
}
