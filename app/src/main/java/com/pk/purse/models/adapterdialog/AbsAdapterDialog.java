package com.pk.purse.models.adapterdialog;

import android.support.v7.app.AlertDialog;

import com.pk.purse.adapter.IOManager;

/**
 * Created by tom on 27/05/16.
 */
public abstract class AbsAdapterDialog implements AdapterDialog {

    protected AlertDialog alertDialog;
    protected IOManager ioManager;

    public AbsAdapterDialog(IOManager ioManager) {
        this.ioManager = ioManager;
    }


}
