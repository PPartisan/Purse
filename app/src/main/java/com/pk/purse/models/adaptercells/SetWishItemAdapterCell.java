package com.pk.purse.models.adaptercells;

import android.util.Log;

/**
 * Created by tom on 27/05/16.
 */
public class SetWishItemAdapterCell extends AbsAdapterCell {

    public SetWishItemAdapterCell(String title, int color) {
        super(title, color);
    }

    @Override
    public void onClick() {
        //wish item dialog
        Log.e(getClass().getSimpleName(), getClass().getSimpleName() + " clicked");
    }
}
