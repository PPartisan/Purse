package com.pk.purse.adapter;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by tom on 27/05/16.
 */
public final class IOManager {

    private final SharedPreferencesManager sharedPreferencesManager;
    private final FileManager fileManager;

    private Context context;

    public IOManager(Context context) {
        this.context = context;
        this.sharedPreferencesManager = new SharedPreferencesManager(PreferenceManager.getDefaultSharedPreferences(context));
        this.fileManager = new FileManager(context);
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public SharedPreferencesManager getSharedPreferencesManager() {
        return sharedPreferencesManager;
    }

    public Context getContext() {
        return context;
    }

}
