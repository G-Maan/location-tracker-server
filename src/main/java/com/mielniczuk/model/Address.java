package com.mielniczuk.model;

import javax.persistence.*;

/**
 * Created by Pawel Mielniczuk on 2016-11-16.
 */
@Entity
@Table(name = "user_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String country;

    @Column
    private String city;

    @Column(name = "street_name")
    private String streetName;

    public Address() {
    }

    public Address(String country, String city, String streetName) {
        this.country = country;
        this.city = city;
        this.streetName = streetName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
