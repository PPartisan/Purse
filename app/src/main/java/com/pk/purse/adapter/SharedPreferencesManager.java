package com.pk.purse.adapter;

import android.content.SharedPreferences;

import java.math.BigDecimal;

/**
 * Created by tom on 27/05/16.
 */
class SharedPreferencesManager {

    private static final String SAVED_MONEY_KEY = "saved_money";

    private SharedPreferences preferences;

    public SharedPreferencesManager(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public BigDecimal getSavedMoney() {
        return new BigDecimal((preferences.getString(SAVED_MONEY_KEY, "0")));
    }

    public void writeSavedMoney(String savedMoney) {
        preferences.edit().putString(SAVED_MONEY_KEY, savedMoney).apply();
    }

}
