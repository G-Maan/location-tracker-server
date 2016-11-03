package com.mielniczuk.rest.controller;

import com.mielniczuk.model.User;
import com.mielniczuk.model.UserLocation;
import com.mielniczuk.repository.UserRepository;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.ws.http.HTTPException;
import java.util.*;


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

    @RequestMapping(value = "/save/{name}/{email:.+}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/add/{email:.+}/{email_friend:.+}", method = RequestMethod.GET)
    public void saveNewFriend(@PathVariable("email") final String email, @PathVariable("email_friend") final String emailFriend){
        User currentUser = userRepository.findByEmail(email);
        User userToAdd = userRepository.findByEmail(emailFriend);

        if(!currentUser.getFriends().contains(userToAdd)){
            currentUser.getFriends().add(userToAdd);
            if(!userToAdd.getFriends().contains(currentUser)){
                userToAdd.getFriends().add(currentUser);
            }
        }

        userRepository.save(currentUser);
        userRepository.save(userToAdd);
    }

    @RequestMapping(value = "/print/friends/{id}", method = RequestMethod.GET)
    public void printFriends(@PathVariable("id") final Long id){
        User user = userRepository.findOne(id);
        user.getFriends().stream().forEach(user1 -> {
            System.out.println("Id: " + user1.getId() + " name: " + user1.getName());
        });
    }

    @RequestMapping(value = "/list/friends/{email:.+}", method = RequestMethod.GET)
    public Set<User> listUserFriends(@PathVariable("email") final String email){
        System.out.println("Passed email: " + email);
        User currentUser = userRepository.findByEmail(email);
        System.out.println("Current user: " + currentUser.toString());
        Set<User> userFriends = currentUser.getFriends();
        return userFriends;

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

    @RequestMapping(value = "/save/location", method = RequestMethod.POST)
    public ResponseEntity<UserLocation> updateLocationOfUser(@RequestBody UserLocation location){
        try {
            System.out.println("Location: ." + location.getEmail() + ". " + location.getLatitude() + " " + location.getLongitude());
            User user = userRepository.findByEmail(location.getEmail());
            user.setLatitude(location.getLatitude());
            user.setLongitude(location.getLongitude());
            userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
        }catch (HTTPException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/find/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findUsersByEmail(@PathVariable String email){
        List<User> users = (ArrayList) userRepository.findByEmailLike(email);
        if(users != null){
            User currentUser = userRepository.findByEmail(email);
            if(users.contains(currentUser)){
                users.remove(currentUser);
            }
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity("No available users" ,HttpStatus.BAD_REQUEST);
        }
    }
}
