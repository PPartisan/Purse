package com.pk.purse.models.adapterdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.pk.purse.R;
import com.pk.purse.adapter.IOManager;
import com.pk.purse.adapter.ViewRecordsAdapter;
import com.pk.purse.models.Record;

import java.util.List;

/**
 * Created by tom on 28/05/16.
 */
public class ViewRecordsDialog extends AbsAdapterDialog implements DialogInterface.OnClickListener {

    final List<Record> records;

    public ViewRecordsDialog(IOManager ioManager) {
        super(ioManager);
        records = ioManager.getRecords();
    }

    @Override
    public AlertDialog getDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ioManager.getContext(), R.style.AlertDialogStyle);

        View view = LayoutInflater.from(ioManager.getContext()).inflate(getLayoutId(), null);

        builder.setView(view);
        builder.setTitle("View Records");
        builder.setPositiveButton("Close", this);
        alertDialog = builder.create();

        RecyclerView rv = (RecyclerView)view.findViewById(R.id.dvr_recycler);
        rv.setAdapter(new ViewRecordsAdapter(records));
        rv.setLayoutManager(new LinearLayoutManager(ioManager.getContext()));
        rv.setHasFixedSize(true);

        return alertDialog;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_viewrecords;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
    }

}
