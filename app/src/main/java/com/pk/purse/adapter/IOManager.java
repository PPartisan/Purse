package com.pk.purse.adapter;

import android.content.Context;

import com.pk.purse.events.UpdatePurseEvent;
import com.pk.purse.models.Record;
import com.pk.purse.models.item.Item;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tom on 27/05/16.
 */
public final class IOManager {

    private static final String TAG = IOManager.class.getSimpleName();

    private final RecordManager recordManager;

    private Context context;

    public IOManager(Context context) {
        this.context = context;
        recordManager = new PurseAbsRecordManager(context);
    }

    public Context getContext() {
        return context;
    }

    public void update(Item item) {
        recordManager.addRecord(new Record(item));
        EventBus.getDefault().post(new UpdatePurseEvent(getRecords()));
    }

    public BigDecimal getSavedMoney() {
        return recordManager.getSavedMoney();
    }

    public List<Record> getRecords() {
        return recordManager.getRecords();
    }

    public void clearRecords() { recordManager.clearAll(); }

    public void setRecords(List<Record> records) {
        recordManager.setRecords(records);
    }

    class PurseAbsRecordManager extends AbsRecordManager {

        public PurseAbsRecordManager(Context context) {
            super(context);
        }

        @Override
        public void onRecordsLoaded(List<Record> records) {
            //Update UI
            recordManager.setRecords(records);
            EventBus.getDefault().post(new UpdatePurseEvent(records));
        }

    }

}