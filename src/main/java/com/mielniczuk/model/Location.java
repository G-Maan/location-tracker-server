package com.mielniczuk.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Pawel Mielniczuk on 2016-11-16.
 */
@Entity
@Table(name = "user_location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private Timestamp date;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address = new Address();

    public Location() {
    }

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(double latitude, double longitude, Address address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
