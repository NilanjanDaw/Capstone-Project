package com.example.nilanjan.visor.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by nilan on 26-Oct-16.
 */
public class CustomEditText extends EditText {
    public CustomEditText(Context context) {
        super(context);
        setFontFace(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFontFace(context);
        setBackgroundTransparent(context);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontFace(context);
        setBackgroundTransparent(context);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFontFace(context);
        setBackgroundTransparent(context);
    }

    private void setFontFace(Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "Kingthings_Calligraphica_Italic.ttf"));
    }

    private void setBackgroundTransparent(Context context) {
        this.setBackgroundColor(Color.TRANSPARENT);
    }
}
