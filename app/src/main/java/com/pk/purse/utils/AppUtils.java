package com.pk.purse.utils;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by tom on 27/05/16.
 */
public final class AppUtils {

    private AppUtils() { throw new AssertionError(); }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String lineSeparator() {
        return (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) ? "\n" : System.lineSeparator();
    }

}
