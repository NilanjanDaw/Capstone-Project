package com.example.nilanjan.visor.utils;

import android.graphics.drawable.Drawable;

/**
 * Created by nilan on 06-Aug-16.
 */
public class OptionsData {

    String optionName;
    Drawable imageResource;

    public OptionsData(String optionName, Drawable imageResource) {
        this.optionName = optionName;
        this.imageResource = imageResource;
    }

    public String getOptionName() {
        return optionName;
    }

    public Drawable getImageResource() {
        return imageResource;
    }
}
