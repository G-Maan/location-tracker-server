package com.mielniczuk.rest.controller;

import com.mielniczuk.model.Customer;
import com.mielniczuk.model.Geolocation;
import com.mielniczuk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
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
        Customer customer = userRepository.findByName(name);
        System.out.println(customer.getId() + " " + customer.getName() + " " + customer.getEmail());
    }

    //TODO: not working
    @RequestMapping(value = "/save/{name}", method = RequestMethod.GET)
    public void saveCustomer(@PathVariable("name") final String name){
        Random rand = new Random();
        double latitude = rand.nextDouble();
        double longitude = rand.nextDouble();
        Customer customer = userRepository.findByName(name);
        if(customer == null){
            userRepository.save(new Customer(name, "email"+name, new Geolocation(latitude, longitude)));
        }else{
            customer.setLocation(new Geolocation(latitude, longitude));
        }
        userRepository.save(customer);
    }
}
