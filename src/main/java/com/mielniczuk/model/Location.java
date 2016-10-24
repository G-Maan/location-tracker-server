package com.mielniczuk.model;

/**
 * Created by Pawel on 2016-10-24.
 */
public class Location {

    public Location() {
    }

    private double latitude;
    private double longitude;

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
}
