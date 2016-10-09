package com.mielniczuk.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pawel on 2016-10-05.
 */
@Entity
@Table(name = "customer")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column
    private double longitude;
    @Column
    private double latitude;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "USER_FRIENDS", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "FRIEND_ID")})
    private Set<User> friends = new HashSet<>();
    @ManyToMany(mappedBy="friends")
    private Set<User> friendOf = new HashSet<>();

    protected User() {}

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }
    public User(String name, String email, double latitude, double longitude){
        this.name = name;
        this.email = email;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<User> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(Set<User> friendOf) {
        this.friendOf = friendOf;
    }
}
