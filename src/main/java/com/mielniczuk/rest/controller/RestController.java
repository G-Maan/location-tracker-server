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
    public String printCustomer(@PathVariable("name") final String name){
        User customer = userRepository.findByName(name);
        String user = customer.getId() + " " + customer.getName() + " " + customer.getEmail();
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/save/{name}/{email}", method = RequestMethod.GET)
    public String saveNewCustomer(@PathVariable("name") final String name, @PathVariable("email") final String email){
        User newCustomer = new User(name, email);
        User savedUser = userRepository.findByName(name);
        if(savedUser == null){
        userRepository.save(newCustomer);
            return "Saved: " + name + " " + email;
        }else{
            return "Not saved, already registered.";
        }
    }

    @RequestMapping(value = "/add/{id}/{id_friend}", method = RequestMethod.GET)
    public void saveNewFriend(@PathVariable("id") final Long id, @PathVariable("id_friend") final Long idFriend){
        User user = userRepository.findOne(id);
        User userToBefriend = userRepository.findOne(idFriend);
        user.getFriends().add(userToBefriend);
        userToBefriend.getFriends().add(user);
        userRepository.save(user);
        userRepository.save(userToBefriend);
    }

    @RequestMapping(value = "/print/friends/{id}", method = RequestMethod.GET)
    public void printFriends(@PathVariable("id") final Long id){
        User user = userRepository.findOne(id);
        user.getFriends().stream().forEach(user1 -> {
            System.out.println("Id: " + user1.getId() + " name: " + user1.getName());
        });
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
