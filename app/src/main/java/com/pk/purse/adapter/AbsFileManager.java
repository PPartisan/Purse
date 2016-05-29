package com.pk.purse.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.pk.purse.models.Record;
import com.pk.purse.models.item.Item;
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
abstract class AbsFileManager implements FileManager{

    private static final String TAG = AbsFileManager.class.getSimpleName();

    public static final String OUTCOME_RECORDS = "outcomeRecords";

    private Context context;

    public AbsFileManager(Context context) {
        this.context = context;
    }

    @Override
    public void readRecords() {
       new ReadRecordsAsyncTask().execute(context);
    }

    @Override
    public void writeRecords(List<Record> records) {

        FileOutputStream outputStream = null;

        try{
            outputStream = context.openFileOutput(OUTCOME_RECORDS, Context.MODE_PRIVATE);
            for(Record record : records) {
                outputStream.write(record.toString().getBytes());
                outputStream.write("\n".getBytes());
            }
            outputStream.close();
        }catch( Exception e ) {
            e.printStackTrace();
        } finally {
            try { outputStream.close(); } catch (Exception e) { e.printStackTrace(); }
        }

    }

    class ReadRecordsAsyncTask extends AsyncTask<Context, Void, List<Record>> {

        @Override
        protected List<Record> doInBackground(Context... params) {

            List<Record> records = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();

            FileInputStream inputStream = null;

            try{
                inputStream = params[0].openFileInput(OUTCOME_RECORDS);
                int character;
                while((character = inputStream.read()) != -1 ) {
                    stringBuilder.append((char)character);
                }
                inputStream.close();
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { inputStream.close(); } catch (Exception e) { e.printStackTrace(); }
            }

            //Log.e("readRecord", stringBuilder.toString());

            if(stringBuilder.length() > 0 ) {
                String[] recordsArray = stringBuilder.toString().split(AppUtils.lineSeparator());
                for(String s: recordsArray) {
                    Log.e(TAG, s);
                    String[] recordArray = s.split(Record.COMMA,4);
                    for (String s1 : recordArray) {
                        Log.e(TAG, s1);
                    }
                    String itemName = recordArray[0];
                    try {
                        double price = Double.parseDouble(recordArray[1]);
                        int quantity = Integer.parseInt(recordArray[2]);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        Date time = simpleDateFormat.parse(recordArray[3], new ParsePosition(0));
                        Item item = Item.Factory.getItemInstance(itemName, String.valueOf(price), quantity);
                        Record record = new Record(item, time);
                        records.add(record);
                    }catch (NumberFormatException e) {
                        Log.e(getClass().getSimpleName(), "NFE", e);
                    }
                }
            }
            return records;
        }

        @Override
        protected void onPostExecute(List<Record> records) {
            onReadRecordsComplete(records);
        }



    }

}
