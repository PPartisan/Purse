package com.pk.purse.models.adapterdialog;

import android.support.v7.app.AlertDialog;

import com.pk.purse.adapter.IOManager;

/**
 * Created by tom on 27/05/16.
 */
public interface AdapterDialog {

    AlertDialog getDialog();
    int getLayoutId();

}
