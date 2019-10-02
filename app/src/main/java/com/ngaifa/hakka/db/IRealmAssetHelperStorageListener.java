package com.ngaifa.hakka.db;

@SuppressWarnings("WeakerAccess")
public interface IRealmAssetHelperStorageListener {

    @SuppressWarnings("UnusedParameters")
    void onLoadedToStorage(String filePath, RealmAssetHelperStatus status);
}
