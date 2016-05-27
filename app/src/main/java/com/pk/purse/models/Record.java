package com.pk.purse.models;

import com.pk.purse.adapter.RecordAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    private Item item;
    private Date time;

    public Record(Item item) {
        this(item, new Date(System.currentTimeMillis()));
    }

    public Record(String name, String price, int quantity) {
        this(new Item(name, price, quantity));
    }

    public Record(Item item, Date time) {
        this.item = item;
        this.time = time;
    }

    public Item getItem() { return item; }

    public Date getTime() { return time; }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder space = new StringBuilder(" ");
        stringBuilder.append(item.name);
        stringBuilder.append(space);
        stringBuilder.append(item.price);
        stringBuilder.append(space);
        stringBuilder.append(item.quantity);
        stringBuilder.append(space);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stringBuilder.append(dateFormat.format(time));
//        stringBuilder.append(time.toString());
        return stringBuilder.toString();
    }
}
