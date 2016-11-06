package com.mielniczuk.model;

import java.text.SimpleDateFormat;

/**
 * Created by Pawel on 2016-10-24.
 */
public class UserLocation {

    public UserLocation() {
    }

    private String email;
    private double latitude;
    private double longitude;
    private String date;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDate() {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }
}
