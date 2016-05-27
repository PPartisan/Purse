package com.pk.purse.models;

import java.math.BigDecimal;

public class Plan {

    private BigDecimal moneyUsedPerDay;
    private int daysUntilPurchasable;

    public Plan(double moneyUsedPerDay, int daysUntilPurchasable) {
        this(BigDecimal.valueOf(moneyUsedPerDay), daysUntilPurchasable);
    }

    public static Plan createPlan(Item item, int days) {
        BigDecimal totalPrice = item.price.multiply(BigDecimal.valueOf(item.quantity));
        MoneyRecorder recorder = MoneyRecorder.getInstance();
        BigDecimal savedMoney = recorder.getSavedMoney();
        BigDecimal savedPerDay = (
                totalPrice.subtract(savedMoney)).divide(BigDecimal.valueOf(days),
                BigDecimal.ROUND_UP
        );

        return new Plan(savedPerDay, days);
    }

    public Plan(BigDecimal moneyUsedPerDay, int daysUntilPurchasable) {
        this.moneyUsedPerDay = moneyUsedPerDay;
        this.daysUntilPurchasable = daysUntilPurchasable;
    }

    public void setMoneyUsedPerDay(BigDecimal moneyUsedPerDay) {
        this.moneyUsedPerDay = moneyUsedPerDay;
    }

    public void setMoneyUsedPerDay(double moneyUsedPerDay) {
        this.setMoneyUsedPerDay(BigDecimal.valueOf(moneyUsedPerDay));
    }

    public void setDaysUntilPurchasable(int daysUntilPurchasable) {
        this.daysUntilPurchasable = daysUntilPurchasable;
    }
}
