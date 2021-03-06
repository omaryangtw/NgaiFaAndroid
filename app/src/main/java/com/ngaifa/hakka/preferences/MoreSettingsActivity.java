package com.ngaifa.hakka.preferences;

import android.os.Bundle;
import android.widget.RadioGroup;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pixplicity.easyprefs.library.Prefs;
import com.ngaifa.hakka.AppPrefs;
import com.ngaifa.hakka.R;

public class MoreSettingsActivity extends AppCompatActivity {

    @BindView(R.id.lomajiSelectionRadioGroup)
    RadioGroup mLomajiSelectionRadioGroup;

//    @BindView(R.id.fontSelectionRadioGroup)
//    RadioGroup mFontSelectionRadioGroup;

    @BindView(R.id.vibrationRadioGroup)
    RadioGroup mVibrationRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_settings);
        ButterKnife.bind(this);

        final int lomajiMode = Prefs.getInt(AppPrefs.PREFS_KEY_CURRENT_INPUT_LOMAJI_MODE_V2, AppPrefs.INPUT_LOMAJI_MODE_APP_DEFAULT);
        if (lomajiMode == AppPrefs.INPUT_LOMAJI_MODE_KIPLMJ) {
            mLomajiSelectionRadioGroup.check(R.id.tailoRadioButton);
        } else if (lomajiMode == AppPrefs.INPUT_LOMAJI_MODE_POJ) {
            mLomajiSelectionRadioGroup.check(R.id.pojRadioButton);
        } else {
            mLomajiSelectionRadioGroup.check(R.id.tailoRadioButton);
        }
        mLomajiSelectionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.tailoRadioButton) {
                    setCurrentInputLomajiMode(AppPrefs.INPUT_LOMAJI_MODE_KIPLMJ);
                } else if (checkedId == R.id.pojRadioButton) {
                    setCurrentInputLomajiMode(AppPrefs.INPUT_LOMAJI_MODE_POJ);
                } else if (checkedId == R.id.englishRadioButton) {
                    setCurrentInputLomajiMode(AppPrefs.INPUT_LOMAJI_MODE_ENGLISH);
                }
            }
        });

//        final int hanjiFontType = Prefs.getInt(AppPrefs.PREFS_KEY_HANJI_FONT_TYPE, AppPrefs.HANJI_FONT_TYPE_APP_DEFAULT);
//        if (hanjiFontType == AppPrefs.HANJI_FONT_TYPE_MINGLIUB) {
//            mFontSelectionRadioGroup.check(R.id.mingliuFontRadioButton);
//        } else if (hanjiFontType == AppPrefs.HANJI_FONT_TYPE_MOEDICT) {
//            mFontSelectionRadioGroup.check(R.id.moedictFontRadioButton);
//        } else if (hanjiFontType == AppPrefs.HANJI_FONT_TYPE_SYSTEM_DEFAULT) {
//            mFontSelectionRadioGroup.check(R.id.systemFontRadioButton);
//        } else {
//            mFontSelectionRadioGroup.check(R.id.mingliuFontRadioButton);
//        }
//        mFontSelectionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                if (checkedId == R.id.mingliuFontRadioButton) {
//                    setCurrentHanjiFont(AppPrefs.HANJI_FONT_TYPE_MINGLIUB);
//                } else if (checkedId == R.id.moedictFontRadioButton) {
//                    setCurrentHanjiFont(AppPrefs.HANJI_FONT_TYPE_MOEDICT);
//                } else if (checkedId == R.id.systemFontRadioButton) {
//                    setCurrentHanjiFont(AppPrefs.HANJI_FONT_TYPE_SYSTEM_DEFAULT);
//                }
//            }
//        });

        final boolean isVibration = Prefs.getBoolean(AppPrefs.PREFS_KEY_IS_VIBRATION, AppPrefs.PREFS_KEY_IS_VIBRATION_YES);
        if (isVibration == AppPrefs.PREFS_KEY_IS_VIBRATION_YES) {
            mVibrationRadioGroup.check(R.id.yesVibrationRadioButton);
        } else if (isVibration == AppPrefs.PREFS_KEY_IS_VIBRATION_NO) {
            mVibrationRadioGroup.check(R.id.noVibrationRadioButton);
        } else {
            mVibrationRadioGroup.check(R.id.yesVibrationRadioButton);
        }
        mVibrationRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.yesVibrationRadioButton) {
                    setCurrentVibration(AppPrefs.PREFS_KEY_IS_VIBRATION_YES);
                } else if (checkedId == R.id.noVibrationRadioButton) {
                    setCurrentVibration(AppPrefs.PREFS_KEY_IS_VIBRATION_NO);
                }
            }
        });
    }

    private void setCurrentVibration(boolean isVibration) {
        Prefs.putBoolean(AppPrefs.PREFS_KEY_IS_VIBRATION, isVibration);
    }

    static void setCurrentInputLomajiMode(int inputMode) {
        if (inputMode == AppPrefs.INPUT_LOMAJI_MODE_ENGLISH) {
            // skip saving EN
        } else {
            Prefs.putInt(AppPrefs.PREFS_KEY_CURRENT_INPUT_LOMAJI_MODE_V2, inputMode);
        }
    }

//    private void setCurrentHanjiFont(int hanjiFontType) {
//        Prefs.putInt(AppPrefs.PREFS_KEY_HANJI_FONT_TYPE, hanjiFontType);
//        RxBus.get().send(new UpdateHanjiFontEvent());
//    }
}
