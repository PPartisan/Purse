package com.pk.purse.adapter;

import android.content.Context;
import android.util.Log;

import com.pk.purse.models.Record;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

abstract class AbsRecordManager implements RecordManager {

    private static final String TAG = AbsRecordManager.class.getSimpleName();

    private FileManager fileManager;

    private List<Record> records;
    private BigDecimal savedMoney = null;

    public AbsRecordManager(Context context){
        records = new ArrayList<>();
        fileManager = new PurseFileManager(context);
    }

    @Override
    public void addRecord(Record record) {
        records.add(record);
        savedMoney = record.getItem().operation(savedMoney);
        AbsRecordManager.this.fileManager.writeRecords(records);
    }

    @Override
    public void setRecords(List<Record> records) {
        this.records = records;
        savedMoney = findSavedMoney(this.records);
        AbsRecordManager.this.fileManager.writeRecords(records);
    }

    @Override
    public List<Record> getRecords() {
        fileManager.readRecords();
        return records;
    }

    @Override
    public BigDecimal getSavedMoney() {
        if (savedMoney == null) {
            savedMoney = new BigDecimal("0");
            fileManager.readRecords();
        }
        return savedMoney;
    }

    @Override
    public void clearAll() {
        Log.e(TAG, "CLEARING RECORDS");
        records.clear();
        savedMoney = findSavedMoney(records);
        AbsRecordManager.this.fileManager.writeRecords(records);
    }

    private static BigDecimal findSavedMoney(List<Record> records) {

        BigDecimal savedMoney = new BigDecimal("0");

        for (Record record : records) {
            savedMoney = record.getItem().operation(savedMoney);
        }

        return savedMoney;

    }

    class PurseFileManager extends AbsFileManager {

        public PurseFileManager(Context context) {
            super(context);
        }

        @Override
        public void onReadRecordsComplete(List<Record> records) {
            AbsRecordManager.this.records = records;
            AbsRecordManager.this.savedMoney = findSavedMoney(records);
            AbsRecordManager.this.onRecordsLoaded(records);
        }

    }

}
