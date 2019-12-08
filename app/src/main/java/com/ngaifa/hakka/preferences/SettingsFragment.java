package com.ngaifa.hakka.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.preference.*;
import com.ngaifa.hakka.AppPrefs;
import com.ngaifa.hakka.R;
import com.pixplicity.easyprefs.library.Prefs;
import com.ngaifa.hakka.ime.TaigiIme;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference);
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }
    @Override
    public void onPause(){
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {



        switch (key){
            case "VIBRATION":
                //boolean isVibration = sharedPreferences.getBoolean(key,false);
                String Vibration = sharedPreferences.getString(key, "false");
                //boolean isVibration;
                if(Vibration.equals("true")){
                    Prefs.putBoolean(AppPrefs.PREFS_KEY_IS_VIBRATION, true);
                } else if(Vibration.equals("false")){
                    Prefs.putBoolean(AppPrefs.PREFS_KEY_IS_VIBRATION, false);
                }




                //Prefs.putBoolean(AppPrefs.PREFS_KEY_IS_VIBRATION, isVibration);

                break;
            case "dialect":
                String dialect = sharedPreferences.getString(key, "sixian");


                break;
            case "system":
                String system = sharedPreferences.getString(key, "INPUT_LOMAJI_MODE_PFS");
                Log.d(TAG,system);
                switch (system) {
                    case "INPUT_LOMAJI_MODE_PFS":
                        //change it back to PFS when finish model design
                        MoreSettingsActivity.setCurrentInputLomajiMode(AppPrefs.INPUT_LOMAJI_MODE_POJ);
                        break;
                    case "INPUT_LOMAJI_MODE_MOE":
                        //change it back to MOE when finish model design
                        MoreSettingsActivity.setCurrentInputLomajiMode(AppPrefs.INPUT_LOMAJI_MODE_KIPLMJ);
                        break;
                    case "INPUT_LOMAJI_MODE_ENGLISH":
                        //change it back to MOE when finish model design
                        MoreSettingsActivity.setCurrentInputLomajiMode(AppPrefs.INPUT_LOMAJI_MODE_ENGLISH);
                        break;
                    default:
                        MoreSettingsActivity.setCurrentInputLomajiMode(AppPrefs.INPUT_LOMAJI_MODE_APP_DEFAULT);
                        break;
                }

                default:
                break;
        }
    }



}
