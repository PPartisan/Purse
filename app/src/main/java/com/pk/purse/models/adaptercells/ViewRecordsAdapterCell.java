package com.pk.purse.models.adaptercells;

import com.pk.purse.adapter.IOManager;
import com.pk.purse.models.adapterdialog.ViewRecordsDialog;

/**
 * Created by tom on 27/05/16.
 */
public final class ViewRecordsAdapterCell extends AbsAdapterCell {

    public ViewRecordsAdapterCell(String title, int color) {
        super(title, color);
    }

    @Override
    public void onClick(IOManager ioManager) {
        new ViewRecordsDialog(ioManager).getDialog().show();
    }
}
