package com.loopcupcakes.apps.polls.viewmodel.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by evin on 1/30/16.
 */
public class SharedPreferencesMagic {
    public static void setChartFlag(Context context){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(Constants.ChartLoadedKey, true);
        editor.apply();
    }

    public static void clearChartFlag(Context context){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(Constants.ChartLoadedKey, false);
        editor.apply();
    }

    public static boolean isChartFlagTrue(Context context){
        return getSharedPreferences(context).getBoolean(Constants.ChartLoadedKey, false);
    }

    private static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(Constants.SharedPreferencesFileKey, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SharedPreferencesFileKey, Context.MODE_PRIVATE);
        return sharedPreferences.edit();
    }
}
