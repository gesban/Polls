package com.loopcupcakes.apps.polls.viewmodel.utils;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.loopcupcakes.apps.polls.R;

/**
 * Created by evin on 1/30/16.
 */
public class MessagesMagic {
    public static void cantConnectMessage(int delay, final View parent, final String message) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Snackbar snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }, delay);
    }
}
