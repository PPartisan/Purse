package com.pk.purse.models.adaptercells;

import com.pk.purse.adapter.IOManager;
import com.pk.purse.models.adapterdialog.AdapterDialog;
import com.pk.purse.models.adapterdialog.OutgoingsDialog;

/**
 * Created by tom on 27/05/16.
 */
public final class OutgoingAdapterCell extends AbsAdapterCell {

    public OutgoingAdapterCell(String title, int color) {
        super(title, color);
    }

    @Override
    public void onClick(IOManager ioManager) {
        AdapterDialog dialog = new OutgoingsDialog(ioManager);
        dialog.getDialog().show();
    }

}
