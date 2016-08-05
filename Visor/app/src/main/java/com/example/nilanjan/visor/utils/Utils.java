package com.example.nilanjan.visor.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by nilanjan on 29-Jul-16.
 */
public class Utils {

    private static String TAG = "Utils";

    public static void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
