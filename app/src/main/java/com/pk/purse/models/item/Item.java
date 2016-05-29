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

    class Factory {

        private Factory() { throw new AssertionError(); }

        public static Item getItemInstance(String name, String price, int quantity) {

            Item item;

            switch (name) {
                case IncomeItem.INCOME_ITEM_NAME:
                    item = new IncomeItem(new BigDecimal(price));
                    break;
                default:
                    item = new OutgoingItem(name, price, quantity);
            }

            return item;

        }

    }

}
