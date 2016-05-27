package com.pk.purse.models.adaptercells;

import android.util.Log;

/**
 * Created by tom on 27/05/16.
 */
public final class ViewRecordsAdapterCell extends AbsAdapterCell {

    public ViewRecordsAdapterCell(String title, int color) {
        super(title, color);
    }

    @Override
    public void onClick() {
        Log.e(getClass().getSimpleName(), getClass().getSimpleName() + " clicked");
    }
}
