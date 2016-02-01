package com.loopcupcakes.apps.polls.viewmodel.utils;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * Created by evin on 1/31/16.
 */
public class ScreenUtils {

    public static boolean isLandscapeAndLongEnough(AppCompatActivity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        boolean isLandscape = activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        boolean isLongEnough = metrics.widthPixels > Constants.minWidthToSplit;

        return isLandscape && isLongEnough;
    }

}
