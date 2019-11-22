package com.ngaifa.hakka.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.preference.*;
import com.ngaifa.hakka.AppPrefs;
import com.ngaifa.hakka.R;
import com.pixplicity.easyprefs.library.Prefs;

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
                boolean isVibration = sharedPreferences.getBoolean(key,false);
                Prefs.putBoolean(AppPrefs.PREFS_KEY_IS_VIBRATION, isVibration);

                break;
            case "dialect":
                String dialect = sharedPreferences.getString(key, "sixian");


                break;
            case "system":
                String system = sharedPreferences.getString(key, "INPUT_LOMAJI_MODE_PFS");
                Log.d(TAG,system);
                if(system.equals("INPUT_LOMAJI_MODE_PFS")){
                    MoreSettingsActivity.setCurrentInputLomajiMode(2);
                }else if(system.equals("INPUT_LOMAJI_MODE_MOE")){
                    MoreSettingsActivity.setCurrentInputLomajiMode(3);
                }else{
                    MoreSettingsActivity.setCurrentInputLomajiMode(-1);
                }

                default:
                break;
        }
    }



}
