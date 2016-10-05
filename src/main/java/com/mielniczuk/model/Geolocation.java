package com.mielniczuk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by Pawel on 2016-10-06.
 */
@Entity
public class Geolocation {

    @Id
    @Column
    private Long id;

    @Column(precision = 8, scale = 6)
    private double latitude;

    @Column(precision = 8, scale = 6)
    private double longitude;

    @OneToOne(mappedBy = "location")
    private Customer customer;


    protected Geolocation() {}

    public Geolocation(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
