package com.example.nilanjan.visor.utils;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nilanjan on 29-Jul-16.
 */
public class Utils {

    private static String TAG = "Utils";

    public static void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static List<MenuData> parseData(JSONArray array) {
        List<MenuData> parsedList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject place = array.getJSONObject(i);
                String name = place.getString("name");
                boolean isOpen = false;
                try {
                    JSONObject opening_info = place.getJSONObject("opening_hours");
                    isOpen = opening_info.optBoolean("open_now", false);
                } catch (Exception e) {
                }
                String photoReference = "";
                try {
                    photoReference = place.getJSONArray("photos").getJSONObject(0).getString("photo_reference");
                } catch (Exception e) {
                }
                double rating = place.optDouble("rating", 0.0);
                String address = place.getString("vicinity");
                double latitude = place.getJSONObject("geometry")
                        .getJSONObject("location").getDouble("lat");
                double longitude = place.getJSONObject("geometry")
                        .getJSONObject("location").getDouble("lng");
                MenuData placeData = new MenuData(name, address, photoReference,
                        latitude, longitude, rating, isOpen);
                parsedList.add(placeData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return parsedList;
    }

}
