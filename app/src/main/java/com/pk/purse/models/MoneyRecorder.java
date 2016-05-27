package com.pk.purse.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MoneyRecorder {

    private List<Record> records;
    private BigDecimal savedMoney;
    private static MoneyRecorder instance;

    private MoneyRecorder() {
        records = new ArrayList<>();
        savedMoney = new BigDecimal("0.00");
    }

    public static MoneyRecorder getInstance() {
        if(instance == null) instance = new MoneyRecorder();
        return instance;
    }

    public void addRecord(Record record) { records.add(record); }

    public void addMoney(double amount) { savedMoney = savedMoney.add(BigDecimal.valueOf(amount)); }

    public void substractMoney(double amount) { savedMoney = savedMoney.subtract(BigDecimal.valueOf(amount)); }

    public void setMoney(double amount) { savedMoney = BigDecimal.valueOf(amount); }

    public void setRecords(List<Record> records) { this.records = records; }

    public List<Record> getRecords() { return records; }

    public BigDecimal getSavedMoney() { return savedMoney; }

}
