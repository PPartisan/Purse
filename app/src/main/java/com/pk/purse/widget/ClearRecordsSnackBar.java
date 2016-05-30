package com.pk.purse.widget;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.pk.purse.R;
import com.pk.purse.adapter.IOManager;
import com.pk.purse.events.UpdatePurseEvent;
import com.pk.purse.models.Record;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 30/05/16.
 */
public final class ClearRecordsSnackBar {

    private ClearRecordsSnackBar() { throw new AssertionError(); }

    public static Snackbar make(Activity activity, final IOManager manager) {

        final List<Record> records = manager.getRecords();
        final List<Record> recordsTemp = new ArrayList<>(records);

        manager.clearRecords();
        EventBus.getDefault().post(new UpdatePurseEvent(manager.getRecords()));

        final View rootView = activity.findViewById(android.R.id.content);
        final int colorRed = ContextCompat.getColor(activity, R.color.adapter_red);
        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Records restored", Toast.LENGTH_SHORT).show();
                manager.setRecords(recordsTemp);
                EventBus.getDefault().post(new UpdatePurseEvent(manager.getRecords()));
            }
        };

        return Snackbar.make(rootView, "Records Cleared", Snackbar.LENGTH_LONG)
                .setAction("Undo", listener).setActionTextColor(colorRed);
    }
}
