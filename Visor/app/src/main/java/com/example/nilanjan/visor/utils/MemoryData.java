package com.example.nilanjan.visor.utils;

import java.io.Serializable;

/**
 * Created by nilan on 26-Oct-16.
 */
public class MemoryData implements Serializable {

    private String id, date, header, body, latitude, longitude;

    public MemoryData(String id, String date, String header, String body, String latitude, String longitude) {
        this.id = id;
        this.date = date;
        this.header = header;
        this.body = body;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
