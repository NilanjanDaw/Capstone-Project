package com.example.nilanjan.visor.utils;

import java.io.Serializable;

/**
 * Created by nilan on 07-Aug-16.
 */
public class MenuData implements Serializable {

    private String title, address, thumbnailReference;
    private double rating, latitude, longitude;
    private boolean isOpen;
    private String placeID;

    public MenuData(String title, String address, String thumbnailReference,
                    double latitude, double longitude, double rating, boolean isOpen, String placeID) {
        this.title = title;
        this.address = address;
        this.thumbnailReference = thumbnailReference;
        this.rating = rating;
        this.isOpen = isOpen;
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeID = placeID;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getThumbnailReference() {
        return thumbnailReference;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getRating() {
        return rating;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getPlaceID() {
        return placeID;
    }
}
