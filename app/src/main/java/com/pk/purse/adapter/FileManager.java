package com.pk.purse.adapter;

import android.content.Context;
import android.util.Log;

import com.pk.purse.models.MoneyRecorder;
import com.pk.purse.models.Record;
import com.pk.purse.models.item.OutgoingItem;
import com.pk.purse.utils.AppUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by tom on 27/05/16.
 */
class FileManager {

    public static final String OUTCOME_RECORDS = "outcomeRecords";

    private Context context;

    private MoneyRecorder moneyRecorder;

    public FileManager(Context context) {
        this.context = context;
    }

    public MoneyRecorder getMoneyRecorder() {
        if (moneyRecorder == null) {
            moneyRecorder = new MoneyRecorder(readRecords());
        }
        return moneyRecorder;
    }

    public List<Record> readRecords() {

        List<Record> records = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        FileInputStream inputStream;

        try{
            inputStream = context.openFileInput(OUTCOME_RECORDS);
            int character;
            while((character = inputStream.read()) != -1 ) {
                stringBuilder.append((char)character);
            }
            inputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        //Log.e("readRecord", stringBuilder.toString());

        if(stringBuilder.length() > 0 ) {
            String[] recordsArray = stringBuilder.toString().split(AppUtils.lineSeparator());
            for(String s: recordsArray) {
                String[] recordArray = s.split(Record.PIPE,4);
                String itemName = recordArray[0];
                try {
                    double price = Double.parseDouble(recordArray[1]);
                    int quantity = Integer.parseInt(recordArray[2]);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    Date time = simpleDateFormat.parse(recordArray[3], new ParsePosition(0));
                    OutgoingItem item = new OutgoingItem(itemName, String.valueOf(price), quantity);
                    Record record = new Record(item, time);
                    records.add(record);
                }catch (NumberFormatException e) {
                    //Log.e(getClass().getSimpleName(), "NFE", e);
                }
            }
        }

        return records;

    }

    public void writeRecords() {
        List<Record> records = getMoneyRecorder().getRecords();
        FileOutputStream outputStream;

        try{
            outputStream = context.openFileOutput(OUTCOME_RECORDS, Context.MODE_PRIVATE);
            for(Record record : records) {
                outputStream.write(record.toString().getBytes());
                outputStream.write("\n".getBytes());
            }
            outputStream.close();
        }catch( Exception e ) {
            e.printStackTrace();
        }
    }

}
