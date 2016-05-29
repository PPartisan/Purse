package com.pk.purse.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pk.purse.R;
import com.pk.purse.events.RecordAdapterClickEvent;
import com.pk.purse.models.adaptercells.AbsAdapterCell;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by tom on 27/05/16.
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private final ArrayList<AbsAdapterCell> cells;

    public RecordAdapter(AdapterCellManager manager) {
        cells = manager.getAdapterCells();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcell_record, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(cells.get(position).title);
        ((CardView)holder.itemView).setCardBackgroundColor(cells.get(position).color);
    }

    @Override
    public int getItemCount() {
        return cells.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.cell_itemname);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new RecordAdapterClickEvent(getAdapterPosition()));
        }
    }

}
