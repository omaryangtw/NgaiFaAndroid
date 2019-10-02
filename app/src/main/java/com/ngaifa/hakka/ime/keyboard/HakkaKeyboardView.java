package com.ngaifa.hakka.ime.keyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import com.ngaifa.hakka.ime.keyboard.CustomKeycode;

public class HakkaKeyboardView extends KeyboardView {

    public HakkaKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HakkaKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean onLongPress(Key key) {
        // space key
        if (key.codes[0] == 32) {
            getOnKeyboardActionListener().onKey(CustomKeycode.KEYCODE_SHOW_IME_PICKER, null);
            return true;
        } else {
            return super.onLongPress(key);
        }
    }
}