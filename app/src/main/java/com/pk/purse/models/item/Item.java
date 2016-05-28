package com.pk.purse.models.item;

import java.math.BigDecimal;

/**
 * Created by tom on 28/05/16.
 */
public interface Item {

    BigDecimal operation(BigDecimal bigDecimal);
    BigDecimal getPrice();
    int getQuantity();
    String getName();

}
