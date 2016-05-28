package com.pk.purse.models.adapterdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import com.pk.purse.R;
import com.pk.purse.adapter.IOManager;
import com.pk.purse.adapter.ViewRecordsAdapter;
import com.pk.purse.models.Record;

/**
 * Created by tom on 28/05/16.
 */
public class ViewRecordsDialog extends AbsAdapterDialog implements DialogInterface.OnClickListener {

    public ViewRecordsDialog(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    public AlertDialog getDialog() {

        if (alertDialog == null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(ioManager.getContext(), R.style.AlertDialogStyle);

            View view = LayoutInflater.from(ioManager.getContext()).inflate(getLayoutId(), null);

            builder.setView(view);
            //builder.setAdapter(getArrayAdapter(),null);
            builder.setTitle("View Records");
            builder.setPositiveButton("Close", this);
            alertDialog = builder.create();

            for (Record record : ioManager.getRecords()) {
                Log.e(getClass().getSimpleName(), record.toString());
            }

            RecyclerView rv = (RecyclerView)view.findViewById(R.id.dvr_recycler);
            rv.setAdapter(new ViewRecordsAdapter(ioManager.getRecords()));
            rv.setLayoutManager(new LinearLayoutManager(ioManager.getContext()));
            rv.setHasFixedSize(true);

        }

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

    private ArrayAdapter<String> getArrayAdapter() {
        Log.e(getClass().getSimpleName(), "ArrayAdapter called");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ioManager.getContext(), android.R.layout.select_dialog_singlechoice);

        Log.e(getClass().getSimpleName(), "Records size: " + ioManager.getRecords().size());
        for (Record record : ioManager.getRecords()) {
            adapter.add(record.getItem().getName());
            Log.e(getClass().getSimpleName(), record.getItem().getName());
        }

        return adapter;

    }
}
