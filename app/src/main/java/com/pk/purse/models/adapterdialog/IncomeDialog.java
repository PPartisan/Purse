package com.pk.purse.models.adapterdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.pk.purse.R;
import com.pk.purse.adapter.IOManager;
import com.pk.purse.events.UpdatePurseEvent;
import com.pk.purse.models.MoneyRecorder;
import com.pk.purse.models.Record;
import com.pk.purse.models.item.IncomeItem;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;

/**
 * Created by tom on 27/05/16.
 */
public class IncomeDialog extends AbsAdapterDialog implements DialogInterface.OnClickListener {

    public IncomeDialog(IOManager manger) {
        super(manger);
    }

    @Override
    public AlertDialog getDialog() {

        if (alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ioManager.getContext(), R.style.AppTheme);

            builder.setView(getLayoutId());
            builder.setTitle("Income");
            builder.setPositiveButton("Confirm", this);
            alertDialog = builder.create();

        }

        return alertDialog;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_addincome;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        AlertDialog alert = (AlertDialog) dialog;

        EditText incomeAmount = (EditText) alert.findViewById(R.id.edittext_incomeamount);

        ioManager.update(new IncomeItem(new BigDecimal(incomeAmount.getText().toString())));

        EventBus.getDefault().post(new UpdatePurseEvent(ioManager.getSavedMoney(IOManager.FILE)));

    }
}