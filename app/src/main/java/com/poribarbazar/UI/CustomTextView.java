package com.poribarbazar.UI;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.poribarbazar.R;


public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {
    Typeface tf;


    public CustomTextView(Context context) {
        super(context);
        tf = ResourcesCompat.getFont(context, R.font.montserrat);
        this.setTypeface(tf);
        int padding = (int) getResources().getDimension(R.dimen.text_padding);
        this.setTextSize( getResources().getDimension(R.dimen.text_size));
        this.setTextColor(Color.parseColor("#1a1a1a"));


        this.setPadding(
                padding,
                padding,
                padding,
                padding
        );
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tf = ResourcesCompat.getFont(context, R.font.montserrat);
        this.setTypeface(tf);
        int padding = (int) getResources().getDimension(R.dimen.text_padding);
        this.setTextSize( getResources().getDimension(R.dimen.text_size));
        this.setTextColor(Color.parseColor("#1a1a1a"));
        this.setPadding(
                padding,
                padding,
                padding,
                padding
        );
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        tf = ResourcesCompat.getFont(context, R.font.montserrat);
        this.setTypeface(tf);
        int padding = (int) getResources().getDimension(R.dimen.text_padding);
        this.setTextSize( getResources().getDimension(R.dimen.text_size));
        this.setTextColor(Color.parseColor("#1a1a1a"));
        this.setPadding(
                padding,
                padding,
                padding,
                padding
        );


    }


}
