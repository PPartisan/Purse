package com.pk.purse.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 * Created by tom on 27/05/16.
 */
public final class ConfigUtils {

    private ConfigUtils() { throw new AssertionError(); }

    public static boolean isInLandscape(Resources res) {
        return (res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

}
