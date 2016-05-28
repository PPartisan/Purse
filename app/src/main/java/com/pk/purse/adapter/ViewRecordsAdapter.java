package com.pk.purse.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pk.purse.R;
import com.pk.purse.models.Record;
import com.pk.purse.models.item.IncomeItem;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by tom on 28/05/16.
 */
public class ViewRecordsAdapter extends RecyclerView.Adapter<ViewRecordsAdapter.ViewHolder> {

    private List<Record> records;
    private final SimpleDateFormat format;

    public ViewRecordsAdapter(List<Record> records) {
        this.records = records;
        format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy", Locale.getDefault());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_viewrecords, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.e(getClass().getSimpleName(), "in On Bind VH");

        holder.name.setText(records.get(position).getItem().getName());
        Log.e(getClass().getSimpleName(), holder.name.getText().toString());
        //holder.quantity.setText(records.get(position).getItem().getQuantity());
        holder.price.setText(NumberFormat.getCurrencyInstance().format(records.get(position).getItem().getPrice().doubleValue()));
        holder.date.setText(format.format(records.get(position).getTime()));

        if (records.get(position).getItem().getName().equals(IncomeItem.INCOME_ITEM_NAME)) {
            final int green = ContextCompat.getColor(holder.itemView.getContext(), R.color.adapter_lt_green);
            holder.name.setTextColor(green);
        }

    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, quantity, price, date;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.rvr_title);
            quantity = (TextView) itemView.findViewById(R.id.rvr_quantity);
            price = (TextView) itemView.findViewById(R.id.rvr_price);
            date = (TextView) itemView.findViewById(R.id.rvr_date);

        }
    }

}
