package com.onedatashare.com.onedatashare.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.EditText;

public class OdsEdiText extends android.support.v7.widget.AppCompatEditText {
    public OdsEdiText(Context context) {
        super(context);
        setNewTypeFace(context);
    }

    private void setNewTypeFace(Context context) {
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Metropolis-Thin.otf");
        setTypeface(custom_font);
    }
}
