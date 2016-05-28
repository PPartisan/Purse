package com.pk.purse.models;

import org.greenrobot.eventbus.util.AsyncExecutor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MoneyRecorder {

    private List<Record> records;
    private BigDecimal savedMoney = null;

    public MoneyRecorder(List<Record> records) {
        this.records = records;
    }

    public void addRecord(Record record) { records.add(record); }

    public List<Record> getRecords() { return records; }

    public BigDecimal getSavedMoney() {
        /*if (savedMoney == null) {

            savedMoney = new BigDecimal("0");

            for (Record record : records) {
                savedMoney = record.getItem().operation(savedMoney);
            }

        }
        return savedMoney;*/
        savedMoney = new BigDecimal("0");

        for (Record record : records) {
            savedMoney = record.getItem().operation(savedMoney);
        }

        return savedMoney;
    }

}
