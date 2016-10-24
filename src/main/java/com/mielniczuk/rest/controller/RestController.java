package com.mielniczuk.rest.controller;

import com.mielniczuk.model.User;
import com.mielniczuk.model.UserLocation;
import com.mielniczuk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.ws.http.HTTPException;
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
        String user = customer.getEmail() + " " + customer.getName();
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

    @RequestMapping(value = "/add/{email}/{email_friend}", method = RequestMethod.GET)
    public void saveNewFriend(@PathVariable("email") final String id, @PathVariable("email_friend") final String idFriend){
        User user = userRepository.findOne(id);
        User userToBefriend = userRepository.findOne(idFriend);
        user.getFriends().add(userToBefriend);
        userToBefriend.getFriends().add(user);
        userRepository.save(user);
        userRepository.save(userToBefriend);
    }

    @RequestMapping(value = "/print/friends/{email}", method = RequestMethod.GET)
    public void printFriends(@PathVariable("email") final String id){
        User user = userRepository.findOne(id);
        user.getFriends().stream().forEach(user1 -> {
            System.out.println("Id: " + user1.getEmail() + " name: " + user1.getName());
        });
    }

    @RequestMapping(value = "/save/random/{email}", method = RequestMethod.GET)
    public void updateCustomerLocation(@PathVariable("email") final String id){
        Random rand = new Random();
        double latitude = rand.nextDouble();
        double longitude = rand.nextDouble();
        User customer = userRepository.findOne(id);
        customer.setLatitude(latitude);
        customer.setLongitude(longitude);
        userRepository.save(customer);
    }

    @RequestMapping(value = "/save/location", method = RequestMethod.POST)
    public ResponseEntity<UserLocation> updateLocationOfUser(@RequestBody UserLocation location){
        try {
            System.out.println("Location: ");
            System.out.println(location);
            User user = userRepository.findOne(location.getEmail());
            System.out.println("User: ");
            System.out.println(user);
            user.setLatitude(location.getLatitude());
            user.setLongitude(location.getLongitude());
            userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
        }catch (HTTPException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
