package com.pk.purse.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pk.purse.R;
import com.pk.purse.adapter.AdapterCellManager;
import com.pk.purse.adapter.RecordAdapter;
import com.pk.purse.events.RecordAdapterClickEvent;
import com.pk.purse.models.Item;
import com.pk.purse.models.MoneyRecorder;
import com.pk.purse.models.Record;
import com.pk.purse.utils.ConfigUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class MainMenuAct extends AppCompatActivity {

    private AdapterCellManager adapterCellManager;

    private TextView savedMoneyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        adapterCellManager = new AdapterCellManager(this);

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
        adapterCellManager.getAdapterCells().get(event.position).onClick();
    }

    private String savedMoneyText() {
        final double savedMoney = MoneyRecorder.getInstance().getSavedMoney().doubleValue();
        final String currencySymbol = Currency.getInstance(Locale.getDefault()).getSymbol();
        Log.e(getClass().getSimpleName(), "Value: " + (currencySymbol + String.valueOf(savedMoney)));
        return getString(R.string.mma_your_purse, (currencySymbol + NumberFormat.getInstance().format(savedMoney)));
    }

    private void readRecords() {
        List<Record> records = new ArrayList<Record>();
        String filename = "outcomeRecords";
        FileInputStream inputStream;
        StringBuilder stringBuilder = new StringBuilder();

        try{
            inputStream = openFileInput(filename);
            int character;
            while( (character = inputStream.read()) != -1 ) {
                stringBuilder.append( (char) character);
            }
            inputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("readRecord", stringBuilder.toString());
        if(stringBuilder.length() > 0 ) {
            String[] recordsArray = stringBuilder.toString().split("\n");
            for(String s: recordsArray) {
                String[] recordArray = s.split(" ",4);
                String itemName = recordArray[0];
                try {
                    double price = Double.parseDouble(recordArray[1]);
                    int quantity = Integer.parseInt(recordArray[2]);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date time = simpleDateFormat.parse(recordArray[3], new ParsePosition(0));
//                Date time = Date.valueOf(recordArray[3]);
                    Item item = new Item(itemName, String.valueOf(price), quantity);
                    Record record = new Record(item, time);
                    records.add(record);
                }catch (NumberFormatException e) {
                    Log.e(getClass().getSimpleName(), "NFE");

                }
            }
        }
        MoneyRecorder.getInstance().setRecords(records);
    }

    private void readSavedMoney() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        double savedMoney = Double.parseDouble(sharedPref.getString("savedMoney", "0"));
        MoneyRecorder.getInstance().setMoney(savedMoney);
    }

    private void writeRecords() {
        List<Record> records = MoneyRecorder.getInstance().getRecords();
        String filename = "outcomeRecords";
        FileOutputStream outputStream;

        try{
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            for(Record record : records) {
                outputStream.write(record.toString().getBytes());
                outputStream.write("\n".getBytes());
            }
            outputStream.close();
        }catch( Exception e ) {
            e.printStackTrace();
        }
    }
    private void writeSavedMoney() {
        Log.i("WRITE", "Current saved money ["+MoneyRecorder.getInstance().getSavedMoney()+"]");
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("savedMoney", String.valueOf(MoneyRecorder.getInstance().getSavedMoney()));
        editor.commit();
    }

    private Dialog createAddIncomeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View dialogView = this.getLayoutInflater().inflate(R.layout.dialog_addincome, null);

        builder.setTitle("Add Income")
                .setView(dialogView)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText incomeAmount = (EditText) dialogView.findViewById(R.id.edittext_incomeamount);
                        MoneyRecorder mr = MoneyRecorder.getInstance();
                        mr.addMoney(Double.parseDouble(incomeAmount.getText().toString()));
                        savedMoneyTextView.setText("Your purse: "+mr.getSavedMoney());
                        writeSavedMoney();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    private Dialog createAddOutcomeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View dialogView = this.getLayoutInflater().inflate(R.layout.dialog_addoutcome, null);

        builder.setTitle("Add Outcome")
                .setView(dialogView)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText itemName = (EditText) dialogView.findViewById(R.id.edittext_itemname);
                        EditText itemQuantity = (EditText) dialogView.findViewById(R.id.edittext_itemquantity);
                        EditText pricePerItem = (EditText) dialogView.findViewById(R.id.edittext_priceperitem);
                        String name = itemName.getText().toString();
                        int quantity = Integer.parseInt(itemQuantity.getText().toString());
                        double price = Double.parseDouble(pricePerItem.getText().toString());
                        Item item = new Item(name, String.valueOf(price), quantity);
                        Record record = new Record(item);
                        MoneyRecorder mr = MoneyRecorder.getInstance();
                        mr.addRecord(record);
                        mr.substractMoney(price * quantity);
                        savedMoneyTextView.setText("Your purse: "+mr.getSavedMoney());
                        writeSavedMoney();
                        writeRecords();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return builder.create();
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
