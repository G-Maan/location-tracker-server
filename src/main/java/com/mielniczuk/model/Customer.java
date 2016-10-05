package com.mielniczuk.model;

import javax.persistence.*;

/**
 * Created by Pawel on 2016-10-05.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_location_id")
    private Geolocation location;

    protected Customer() {}

    public Customer(String name, String email){
        this.name = name;
        this.email = email;
    }
    public Customer(String name, String email, Geolocation location){
        this.name = name;
        this.email = email;
        this.location = location;
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

    public Geolocation getLocation() {
        return location;
    }

    public void setLocation(Geolocation location) {
        this.location = location;
    }
}
