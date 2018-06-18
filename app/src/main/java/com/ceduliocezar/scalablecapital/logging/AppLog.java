package com.ceduliocezar.scalablecapital.logging;

import android.util.Log;

public class AppLog {


    public static void d(String tag, String message) {
        Log.d(tag, message);
    }

    public static void e(String tag, String message) {
        Log.e(tag, message);
    }

    public static void i(String tag, String message) {
        Log.e(tag, message);
    }


    public static void e(String tag, String s, Exception exception) {
        Log.e(tag, s, exception);
    }
}
