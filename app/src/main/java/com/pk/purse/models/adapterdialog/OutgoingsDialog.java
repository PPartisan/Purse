package com.pk.purse.models.adapterdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.pk.purse.R;
import com.pk.purse.adapter.IOManager;
import com.pk.purse.events.UpdatePurseEvent;
import com.pk.purse.models.MoneyRecorder;
import com.pk.purse.models.Record;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by tom on 27/05/16.
 */
public class OutgoingsDialog extends AbsAdapterDialog implements DialogInterface.OnClickListener {

    public OutgoingsDialog(IOManager manger) {
        super(manger);
    }

    @Override
    public AlertDialog getDialog() {

        if (alertDialog == null) {

            //LayoutInflater inflater = LayoutInflater.from(context);
            //final View view = inflater.inflate(getLayoutId(), null);
            AlertDialog.Builder builder = new AlertDialog.Builder(ioManager.getContext(), R.style.AppTheme);

            builder.setView(getLayoutId());
            builder.setTitle("Outgoings");
            builder.setPositiveButton("Confirm", this);
            alertDialog = builder.create();

        }

        return alertDialog;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_addoutcome;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        AlertDialog alert = (AlertDialog) dialog;

        EditText itemName = (EditText) alert.findViewById(R.id.edittext_itemname);
        EditText itemQuantity = (EditText) alert.findViewById(R.id.edittext_itemquantity);
        EditText pricePerItem = (EditText) alert.findViewById(R.id.edittext_priceperitem);

        final String name = itemName != null ? itemName.getText().toString() : null;
        final int quantity = Integer.parseInt(itemQuantity != null ? itemQuantity.getText().toString() : null);
        final double price = Double.parseDouble(pricePerItem != null ? pricePerItem.getText().toString() : null);

        Record record = new Record(name, String.valueOf(price), quantity);
        MoneyRecorder mr = ioManager.getFileManager().getMoneyRecorder();
        mr.addRecord(record);
        mr.substractMoney(price * quantity);

        ioManager.getFileManager().writeRecords();
        ioManager.getSharedPreferencesManager().writeSavedMoney(mr.getSavedMoney().toPlainString());

        EventBus.getDefault().post(new UpdatePurseEvent(mr.getSavedMoney()));

    }
}
