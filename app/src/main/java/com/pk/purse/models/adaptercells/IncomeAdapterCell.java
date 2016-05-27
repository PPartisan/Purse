package com.pk.purse.models.adaptercells;

import android.util.Log;

/**
 * Created by tom on 27/05/16.
 */
public final class IncomeAdapterCell extends AbsAdapterCell{

    public IncomeAdapterCell(String title, int color) {
        super(title, color);
    }

    @Override
    public void onClick() {
        //Income dialog
        Log.e(getClass().getSimpleName(), getClass().getSimpleName() + " clicked");
    }

}
