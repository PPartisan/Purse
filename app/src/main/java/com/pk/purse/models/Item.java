package com.pk.purse.models;

import java.math.BigDecimal;

public class Item {

    //Immutable
    public final String name;
    public final int quantity;
    public final BigDecimal price;

    public Item(String name, String price, int quantity) {
        this(name, new BigDecimal(price), quantity);
    }

    public Item(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
