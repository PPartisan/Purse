package com.pk.purse.adapter;

import android.content.Context;
import android.preference.PreferenceManager;

import com.pk.purse.models.MoneyRecorder;
import com.pk.purse.models.Record;
import com.pk.purse.models.item.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tom on 27/05/16.
 */
public final class IOManager {

    public static final String FILE = "file_key";
    public static final String PREFS = "shared_prefs_key";

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

    public void update(Item item) {

        MoneyRecorder recorder = fileManager.getMoneyRecorder();
        recorder.addRecord(new Record(item));

        fileManager.writeRecords();
        sharedPreferencesManager.writeSavedMoney(fileManager.getMoneyRecorder().getSavedMoney().toPlainString());
    }

    public BigDecimal getSavedMoney(String key) {

        BigDecimal savedMoney;

        switch (key) {
            case FILE:
                savedMoney = fileManager.getMoneyRecorder().getSavedMoney();
                break;
            case PREFS:
                savedMoney = sharedPreferencesManager.getSavedMoney();
                break;
            default:
                throw new IllegalArgumentException("key value must match constant in "
                        + this.getClass().getCanonicalName());
        }

        return savedMoney;
    }

    public List<Record> getRecords() {
        return fileManager.getMoneyRecorder().getRecords();
    }

}
