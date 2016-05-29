package com.pk.purse.adapter;

import com.pk.purse.models.Record;

import java.util.List;

/**
 * Created by tom on 29/05/16.
 */
public interface FileManager {

    void writeRecords(List<Record> records);
    void readRecords();
    void onReadRecordsComplete(List<Record> records);

}
