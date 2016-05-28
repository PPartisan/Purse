package com.pk.purse.models.item;

import java.math.BigDecimal;

/**
 * Created by tom on 28/05/16.
 */
public class IncomeItem implements Item {

    public static final String INCOME_ITEM_NAME = "Income";

    private final BigDecimal value;

    public IncomeItem(BigDecimal value){
        this.value = value;
    }

    @Override
    public BigDecimal operation(BigDecimal bigDecimal) {
        return bigDecimal.add(value);
    }

    @Override
    public BigDecimal getPrice() {
        return value;
    }

    @Override
    public int getQuantity() {
        return 1;
    }

    @Override
    public String getName() {
        return INCOME_ITEM_NAME;
    }
}
