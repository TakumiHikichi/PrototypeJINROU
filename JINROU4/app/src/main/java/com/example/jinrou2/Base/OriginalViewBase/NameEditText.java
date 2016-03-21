package com.example.jinrou2.Base.OriginalViewBase;

import android.content.Context;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;

/**
 * Created by ryouta on 2016/01/10.
 */
public class NameEditText extends EditText {
    public NameEditText(Context context) {
        super(context);
        InputFilter[] inputFilter = new InputFilter[1];
        inputFilter[0] = new InputFilter.LengthFilter(10);
        this.setFilters(inputFilter);

        this.setInputType(InputType.TYPE_CLASS_TEXT);
        this.setBackgroundColor(Color.WHITE);
        this.setTextColor(Color.BLACK);


    }
}
