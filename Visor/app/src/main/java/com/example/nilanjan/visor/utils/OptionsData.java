package com.example.nilanjan.visor.utils;

/**
 * Created by nilan on 06-Aug-16.
 */
public class OptionsData {

    String optionName;
    int imageResource;

    public OptionsData(String optionName, int imageResource) {
        this.optionName = optionName;
        this.imageResource = imageResource;
    }

    public String getOptionName() {
        return optionName;
    }

    public int getImageResource() {
        return imageResource;
    }
}
