package com.example.nilanjan.visor.utils;


import android.app.Application;

/**
 * Created by nilan on 31-Jul-16.
 */
public class Constants extends Application {

    public static final int imageResolution = 800;
    public static final String[] PLACES_OF_INTEREST = {"amusement_park", "aquarium", "art_gallery", "casino", "church",
            "movie_theater", "museum", "night_club", "park", "shopping_mall", "stadium", "zoo"};
    public static final String photoBaseURL = "https://maps.googleapis.com/maps/api/place/photo";
    /**
     * Enter Google Places API key here:
     */
    public static String API_KEY = "ENTER_API_KEY";
}
