package com.pk.purse.adapter;

import com.pk.purse.models.Record;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tom on 29/05/16.
 */
public interface RecordManager {

    void onRecordsLoaded(List<Record> records);

    BigDecimal getSavedMoney();
    void addRecord(Record record);
    void setRecords(List<Record> records);
    List<Record> getRecords();


}

