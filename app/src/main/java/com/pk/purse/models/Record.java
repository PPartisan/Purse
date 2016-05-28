package com.pk.purse.models;

import com.pk.purse.models.item.Item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Record {

    public static final String PIPE = "|";

    private Item item;
    private Date time;

    public Record(Item item) {
        this(item, new Date(System.currentTimeMillis()));
    }

    public Record(Item item, Date time) {
        this.item = item;
        this.time = time;
    }

    public Item getItem() { return item; }

    public Date getTime() { return time; }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return item.getName() + PIPE + item.getPrice() + PIPE +
                item.getQuantity() + PIPE + dateFormat.format(time);
    }
}
