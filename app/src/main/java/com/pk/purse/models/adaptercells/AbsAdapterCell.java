package com.pk.purse.models.adaptercells;

/**
 * Created by tom on 27/05/16.
 */
public abstract class AbsAdapterCell implements AdapterCell {

    public final String title;
    public final int color;

    public AbsAdapterCell(String title, int color) {
        this.title = title;
        this.color = color;
    }

}
