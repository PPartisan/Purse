package com.pk.purse.models.adapterdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.EditText;

import com.pk.purse.R;
import com.pk.purse.adapter.IOManager;
import com.pk.purse.models.item.OutgoingItem;

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

            AlertDialog.Builder builder = new AlertDialog.Builder(ioManager.getContext(), R.style.AlertDialogStyle);

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

        if (TextUtils.isEmpty(itemName.getText()) ||
                TextUtils.isEmpty(itemQuantity.getText()) ||
                TextUtils.isEmpty(pricePerItem.getText())) {
            dialog.dismiss();
            return;
        }

        final String name = itemName.getText().toString();
        final int quantity = Integer.parseInt(itemQuantity != null ? itemQuantity.getText().toString() : "0");
        final String price = pricePerItem != null ? pricePerItem.getText().toString() : null;

        ioManager.update(new OutgoingItem(name, price, quantity));

        //EventBus.getDefault().post(new UpdatePurseEvent(ioManager.getRecords()));

    }
}
