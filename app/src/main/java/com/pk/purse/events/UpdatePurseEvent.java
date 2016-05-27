package com.pk.purse.events;

import java.math.BigDecimal;

/**
 * Created by tom on 27/05/16.
 */
public class UpdatePurseEvent {

    public BigDecimal purse;

    public UpdatePurseEvent(BigDecimal purse) {
        this.purse = purse;
    }

}
