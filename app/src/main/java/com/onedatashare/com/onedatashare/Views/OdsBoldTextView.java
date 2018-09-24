package com.onedatashare.com.onedatashare.Views;


import android.content.Context;
import android.graphics.Typeface;

public class OdsBoldTextView extends android.support.v7.widget.AppCompatTextView {

    public OdsBoldTextView(Context context) {
        super(context);
        setNewTypeFace(context);
    }

    private void setNewTypeFace(Context context) {
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Metropolis-Bold.otf");
        setTypeface(custom_font);
    }
}