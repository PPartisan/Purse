package com.pk.purse.utils;

import com.pk.purse.models.item.Item;

import java.math.BigDecimal;

/**
 * Created by tom on 29/05/16.
 */
public final class ItemUtils {

    private ItemUtils() { throw new AssertionError(); }

    public static BigDecimal getTotalPriceOfItem(Item item) {
        return item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
    }

}
