package com.herowars.template.utils;

import android.util.Log;

import com.herowars.template.BuildConfig;

public class LogUtils {

    private LogUtils() {
        // private constructor
    }
    //region Public Methods ************************************************************************

    // Verbose /////////////////////////////////////////////////////////////////////////////////////

    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, msg, tr);
        }
    }

    // Debug ///////////////////////////////////////////////////////////////////////////////////////

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg, tr);
        }
    }

    // Info ////////////////////////////////////////////////////////////////////////////////////////

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg, tr);
        }
    }

    // Warn ////////////////////////////////////////////////////////////////////////////////////////

    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg, tr);
        }
    }

    // Error ///////////////////////////////////////////////////////////////////////////////////////

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg, tr);
        }
    }

    //endregion

    //region Private Methods ***********************************************************************


    //endregion
}
