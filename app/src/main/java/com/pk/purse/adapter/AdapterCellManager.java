package com.pk.purse.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.pk.purse.R;
import com.pk.purse.models.adaptercells.AbsAdapterCell;
import com.pk.purse.models.adaptercells.IncomeAdapterCell;
import com.pk.purse.models.adaptercells.OutgoingAdapterCell;
import com.pk.purse.models.adaptercells.SetWishItemAdapterCell;
import com.pk.purse.models.adaptercells.ViewRecordsAdapterCell;

import java.util.ArrayList;

/**
 * Created by tom on 27/05/16.
 */
public final class AdapterCellManager {

    private ArrayList<AbsAdapterCell> cells = null;

    public AdapterCellManager(Context context) {
        cells = getAdapterCells(context);
    }

    public ArrayList<AbsAdapterCell> getAdapterCells() {
        return cells;
    }

    private ArrayList<AbsAdapterCell> getAdapterCells(Context context) {

        final String[] titles = context.getResources().getStringArray(R.array.adapter_entries);
        final int[] colors = getColorsIds();

        final ArrayList<AbsAdapterCell> cells = new ArrayList<>(titles.length);

        cells.add(new OutgoingAdapterCell(titles[0], ContextCompat.getColor(context, colors[0])));
        cells.add(new IncomeAdapterCell(titles[1], ContextCompat.getColor(context, colors[1])));
        cells.add(new SetWishItemAdapterCell(titles[2], ContextCompat.getColor(context, colors[2])));
        cells.add(new ViewRecordsAdapterCell(titles[3], ContextCompat.getColor(context, colors[3])));

        return cells;

    }

    private static int[] getColorsIds() {
        return new int[] {
                R.color.adapter_amber, R.color.adapter_blue,
                R.color.adapter_lt_green, R.color.adapter_purple
        };
    }

}
