package com.pk.purse.models.item;

import java.math.BigDecimal;

/**
 * Created by tom on 28/05/16.
 */
public class OutgoingItem implements Item {

    private final String name;
    private final int quantity;
    private final BigDecimal price;

    public OutgoingItem(String name, String price, int quantity) {
        this(name, new BigDecimal(price), quantity);
    }

    public OutgoingItem(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public BigDecimal operation(BigDecimal bigDecimal) {
        return bigDecimal.subtract(price.multiply(BigDecimal.valueOf(quantity)));
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getName() {
        return name;
    }

}
