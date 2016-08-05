package com.example.nilanjan.visor.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by nilan on 29-Jul-16.
 */
public class HeaderTextView extends TextView {


    public HeaderTextView(Context context) {
        super(context);
        setFontFace(context);
    }

    public HeaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFontFace(context);
    }

    public HeaderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontFace(context);
    }

    public HeaderTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFontFace(context);
    }

    private void setFontFace(Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "Kingthings_Calligraphica_Italic.ttf"));
    }
}
