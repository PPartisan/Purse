package com.pk.purse.models.adaptercells;

import android.content.Context;
import android.util.Log;

import com.pk.purse.adapter.IOManager;

/**
 * Created by tom on 27/05/16.
 */
public final class ViewRecordsAdapterCell extends AbsAdapterCell {

    public ViewRecordsAdapterCell(String title, int color) {
        super(title, color);
    }

    @Override
    public void onClick(IOManager ioManager) {
        Log.e(getClass().getSimpleName(), getClass().getSimpleName() + " clicked");
    }
}
