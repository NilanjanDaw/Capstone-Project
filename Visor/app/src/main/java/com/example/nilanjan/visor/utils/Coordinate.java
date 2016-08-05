package com.example.nilanjan.visor.utils;

import java.io.Serializable;

/**
 * Created by nilan on 29-Jul-16.
 */
public class Coordinate implements Serializable {

    private double latitude;
    private double longitude;
    private String placeID;
    private String name;
    private String photoReference;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        placeID = "";
        name = "";
        photoReference = "";
    }

    public Coordinate(Coordinate coordinate) {
        this.latitude = coordinate.latitude;
        this.longitude = coordinate.longitude;
        this.placeID = coordinate.placeID;
        this.name = coordinate.name;
        this.photoReference = coordinate.photoReference;
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

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }
}
