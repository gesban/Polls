package com.loopcupcakes.apps.polls.viewmodel.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

import java.util.Date;

public class ConnectivityReceiver extends BroadcastReceiver {
    private static final String TAG = Constants.ConnectivityReceiverTAG_;

    public ConnectivityReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + new Date());
        Log.d(TAG, "onReceive: " + isOnline(context));
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
