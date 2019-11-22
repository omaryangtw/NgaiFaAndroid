package com.ngaifa.hakka;

import android.app.Application;
import android.content.ContextWrapper;
//import com.crashlytics.android.Crashlytics;
//import com.crashlytics.android.core.CrashlyticsCore;
import com.pixplicity.easyprefs.library.Prefs;
import com.ngaifa.hakka.db.IRealmAssetHelperStorageListener;
import com.ngaifa.hakka.db.RealmAssetHelper;
import com.ngaifa.hakka.db.RealmAssetHelperStatus;
//import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NgaiFaHakkaApp extends Application {

    public static final String DATABASE_ASSETS_PATH = "preload_realm_db";
    public static final String DATABASE_BASE_NAME = "ime_dict";

    @Override
    public void onCreate() {
        super.onCreate();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        Realm.init(this);
        RealmAssetHelper.getInstance(this).loadDatabaseToStorage(DATABASE_ASSETS_PATH, DATABASE_BASE_NAME, new IRealmAssetHelperStorageListener() {
            @Override
            public void onLoadedToStorage(String realmDbName, RealmAssetHelperStatus realmAssetHelperStatus) {
                RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                        .name(realmDbName)
                        .build();
                Realm.setDefaultConfiguration(realmConfig);
            }
        });
    }
}
