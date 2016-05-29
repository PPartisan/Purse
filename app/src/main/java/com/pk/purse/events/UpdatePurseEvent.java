package com.pk.purse.events;

import com.pk.purse.models.Record;

import java.util.List;

/**
 * Created by tom on 27/05/16.
 */
public class UpdatePurseEvent {

    public List<Record> records;

    public UpdatePurseEvent(List<Record> records) {
        this.records = records;
    }

}
