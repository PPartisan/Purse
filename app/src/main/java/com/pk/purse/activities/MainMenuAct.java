package com.pk.purse.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pk.purse.R;
import com.pk.purse.adapter.AdapterCellManager;
import com.pk.purse.adapter.IOManager;
import com.pk.purse.adapter.RecordAdapter;
import com.pk.purse.events.RecordAdapterClickEvent;
import com.pk.purse.events.UpdatePurseEvent;
import com.pk.purse.models.Item;
import com.pk.purse.models.MoneyRecorder;
import com.pk.purse.models.Record;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.NumberFormat;

public class MainMenuAct extends AppCompatActivity {

    private AdapterCellManager adapterCellManager;
    private IOManager ioManager;

    private TextView savedMoneyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        adapterCellManager = new AdapterCellManager(this);
        ioManager = new IOManager(this);

        savedMoneyTextView = (TextView) findViewById(R.id.savedmoney_tv);
        savedMoneyTextView.setText(savedMoneyText());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.menu_rv);

        if (recyclerView != null) {
            recyclerView.setAdapter(new RecordAdapter(adapterCellManager));
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setHasFixedSize(true);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(RecordAdapterClickEvent event) {
        adapterCellManager.getAdapterCells().get(event.position).onClick(ioManager);
    }

    @Subscribe
    public void onEvent(UpdatePurseEvent event) {
        savedMoneyTextView.setText(savedMoneyText());
    }

    private String savedMoneyText() {
        final double savedMoney = ioManager.getFileManager().getMoneyRecorder().getSavedMoney().doubleValue();
        return getString(R.string.mma_your_purse, (NumberFormat.getCurrencyInstance().format(savedMoney)));
    }

    private Dialog createSetWishedItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View dialogView = this.getLayoutInflater().inflate(R.layout.dialog_setwisheditem, null);

        builder.setTitle("Set Wished Item")
                .setView(dialogView)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }



}
