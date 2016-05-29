package com.pk.purse.models.adaptercells;

import android.widget.Toast;

import com.pk.purse.adapter.IOManager;

/**
 * Created by tom on 27/05/16.
 */
public class SetWishItemAdapterCell extends AbsAdapterCell {

    public SetWishItemAdapterCell(String title, int color) {
        super(title, color);
    }

    @Override
    public void onClick(IOManager ioManager) {
        Toast.makeText(ioManager.getContext(), getClass().getSimpleName() + " clicked", Toast.LENGTH_SHORT).show();
    }
}
