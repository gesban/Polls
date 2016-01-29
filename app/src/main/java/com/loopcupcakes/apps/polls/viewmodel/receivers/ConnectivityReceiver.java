package com.loopcupcakes.apps.polls.viewmodel.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.loopcupcakes.apps.polls.viewmodel.services.UpdateDataService;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;
import com.loopcupcakes.apps.polls.viewmodel.utils.NetworkMagic;

import java.util.Date;

public class ConnectivityReceiver extends BroadcastReceiver {
    private static final String TAG = Constants.ConnectivityReceiverTAG_;
    private static boolean hasRun = false;

    public ConnectivityReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + new Date());
        Log.d(TAG, "onReceive: " + NetworkMagic.isOnline(context));

        if (NetworkMagic.isOnline(context) && !hasRun){
            Intent intentService = new Intent(context, UpdateDataService.class);
            context.startService(intentService);
        }
    }
}
