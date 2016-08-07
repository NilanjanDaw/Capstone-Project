package com.example.nilanjan.visor.utils;

/**
 * Created by nilan on 07-Aug-16.
 */
public class MenuData {

    private String title, address, thumbnailReference, rating;
    private boolean isOpen;

    public MenuData(String title, String address, String thumbnailReference, String rating, boolean isOpen) {
        this.title = title;
        this.address = address;
        this.thumbnailReference = thumbnailReference;
        this.rating = rating;
        this.isOpen = isOpen;
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

    public String getRating() {
        return rating;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
