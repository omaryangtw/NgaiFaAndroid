package com.ngaifa.hakka.ime.keyboard;

import android.content.res.Resources;
import android.inputmethodservice.Keyboard;
import android.view.inputmethod.InputMethodManager;

import com.ngaifa.hakka.R;
import com.ngaifa.hakka.ime.TaigiIme;
import com.ngaifa.hakka.ime.candidate.CandidateView;

public class KeyboardSwitcher {

    public static final int KEYBOARD_TYPE_LOMAJI_QWERTY = 0;
    public static final int KEYBOARD_TYPE_HANJI_QWERTY = 1;
    public static final int KEYBOARD_TYPE_LOMAJI_SYMBOL = 2;
    public static final int KEYBOARD_TYPE_LOMAJI_SYMBOL_SHIFTED = 3;
    public static final int KEYBOARD_TYPE_HANJI_SYMBOL = 4;
    public static final int KEYBOARD_TYPE_HANJI_SYMBOL_SHIFTED = 5;

    private final TaigiIme mTaigiIme;
    private final InputMethodManager mInputMethodManager;

    private HakkaKeyboardView mHakkaKeyboardView;
    private CandidateView mCandidateView;

    private HakkaKeyboard mLomajiQwertyKeyboard;
    private HakkaKeyboard mHanjiQwertyKeyboard;
    private HakkaKeyboard mLomajiSymbolsKeyboard;
    private HakkaKeyboard mLomajiSymbolsShiftedKeyboard;
    private HakkaKeyboard mHanjiSymbolsKeyboard;
    private HakkaKeyboard mHanjiSymbolsShiftedKeyboard;

    private HakkaKeyboard mCurrentKeyboard;

    private int mImeOptions;

    public KeyboardSwitcher(TaigiIme taigiIme, InputMethodManager inputMethodManager) {
        mTaigiIme = taigiIme;
        mInputMethodManager = inputMethodManager;

        mLomajiQwertyKeyboard = new HakkaKeyboard(taigiIme, R.xml.keyboard_layout_lomaji_qwerty);
        mHanjiQwertyKeyboard = new HakkaKeyboard(taigiIme, R.xml.keyboard_layout_hanji_qwerty);
        mLomajiSymbolsKeyboard = new HakkaKeyboard(taigiIme, R.xml.keyboard_layout_lomaji_symbols);
        mLomajiSymbolsShiftedKeyboard = new HakkaKeyboard(taigiIme, R.xml.keyboard_layout_lomaji_symbols_shift);
        mHanjiSymbolsKeyboard = new HakkaKeyboard(taigiIme, R.xml.keyboard_layout_hanji_symbols);
        mHanjiSymbolsShiftedKeyboard = new HakkaKeyboard(taigiIme, R.xml.keyboard_layout_hanji_symbols_shift);

        mCurrentKeyboard = mLomajiQwertyKeyboard;
    }

    public void setTaigiCandidateView(CandidateView candidateView) {
        mCandidateView = candidateView;
    }

//    public TaigiKeyboard getCurrentKeyboard() {
//        return (TaigiKeyboard) mTaigiKeyboardView.getKeyboard();
//    }

    public void setKeyboardByType(int keyboardType) {
        HakkaKeyboard nextKeyboard;

        if (keyboardType == KEYBOARD_TYPE_LOMAJI_QWERTY) {
            nextKeyboard = mLomajiQwertyKeyboard;
        } else if (keyboardType == KEYBOARD_TYPE_HANJI_QWERTY) {
            nextKeyboard = mHanjiQwertyKeyboard;
        } else if (keyboardType == KEYBOARD_TYPE_LOMAJI_SYMBOL) {
            nextKeyboard = mLomajiSymbolsKeyboard;
        } else if (keyboardType == KEYBOARD_TYPE_LOMAJI_SYMBOL_SHIFTED) {
            nextKeyboard = mLomajiSymbolsShiftedKeyboard;
        } else if (keyboardType == KEYBOARD_TYPE_HANJI_SYMBOL) {
            nextKeyboard = mHanjiSymbolsKeyboard;
        } else if (keyboardType == KEYBOARD_TYPE_HANJI_SYMBOL_SHIFTED) {
            nextKeyboard = mHanjiSymbolsShiftedKeyboard;
        } else {
            nextKeyboard = mLomajiQwertyKeyboard;
        }

        mCurrentKeyboard = nextKeyboard;

        setKeyboard(nextKeyboard);
    }

    public void resetKeyboard(HakkaKeyboardView hakkaKeyboardView) {
        mHakkaKeyboardView = hakkaKeyboardView;
        setKeyboard(mCurrentKeyboard);
    }

    public boolean isCurrentKeyboardViewUseQwertyKeyboard() {
        return mCurrentKeyboard == mLomajiQwertyKeyboard
                || mCurrentKeyboard == mHanjiQwertyKeyboard;
    }

    public void switchKeyboard() {
        HakkaKeyboard currentKeyboard = (HakkaKeyboard) mHakkaKeyboardView.getKeyboard();
        HakkaKeyboard nextKeyboard = mLomajiQwertyKeyboard;

        if (currentKeyboard == mLomajiQwertyKeyboard) {
            nextKeyboard = mLomajiSymbolsKeyboard;
        } else if (currentKeyboard == mHanjiQwertyKeyboard) {
            nextKeyboard = mHanjiSymbolsKeyboard;
        } else if (currentKeyboard == mLomajiSymbolsKeyboard || currentKeyboard == mLomajiSymbolsShiftedKeyboard) {
            nextKeyboard = mLomajiQwertyKeyboard;
        } else if (currentKeyboard == mHanjiSymbolsKeyboard || currentKeyboard == mHanjiSymbolsShiftedKeyboard) {
            nextKeyboard = mHanjiQwertyKeyboard;
        }

        mCurrentKeyboard = nextKeyboard;

        setKeyboard(nextKeyboard);
    }

    public void handleShift() {
        Keyboard currentKeyboard = mHakkaKeyboardView.getKeyboard();
        HakkaKeyboard nextKeyboard = mLomajiQwertyKeyboard;

        if (currentKeyboard == mLomajiSymbolsKeyboard) {
            nextKeyboard = mLomajiSymbolsShiftedKeyboard;
        } else if (currentKeyboard == mLomajiSymbolsShiftedKeyboard) {
            nextKeyboard = mLomajiSymbolsKeyboard;
        } else if (currentKeyboard == mHanjiSymbolsKeyboard) {
            nextKeyboard = mHanjiSymbolsShiftedKeyboard;
        } else if (currentKeyboard == mHanjiSymbolsShiftedKeyboard) {
            nextKeyboard = mHanjiSymbolsKeyboard;
        }

        mCurrentKeyboard = nextKeyboard;

        setKeyboard(nextKeyboard);
    }

    private void setKeyboard(HakkaKeyboard nextKeyboard) {
        nextKeyboard.setImeOptions(mTaigiIme.getResources(), mImeOptions);

        if (mHakkaKeyboardView != null) {
            mHakkaKeyboardView.setKeyboard(nextKeyboard);
        }

        if (mCandidateView != null) {
            if (nextKeyboard == mLomajiQwertyKeyboard
                    || nextKeyboard == mLomajiSymbolsKeyboard
                    || nextKeyboard == mLomajiSymbolsShiftedKeyboard) {
                mCandidateView.setIsMainCandidateLomaji(true);
            } else {
                mCandidateView.setIsMainCandidateLomaji(false);
            }
        }
    }

    public void setImeOptions(Resources resources, int imeOptions) {
        mImeOptions = imeOptions;
        mCurrentKeyboard.setImeOptions(resources, imeOptions);
    }
}
