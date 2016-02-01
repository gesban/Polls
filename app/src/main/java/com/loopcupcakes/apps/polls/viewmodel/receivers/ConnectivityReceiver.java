package com.loopcupcakes.apps.polls.viewmodel.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.loopcupcakes.apps.polls.viewmodel.services.UpdateDataService;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;
import com.loopcupcakes.apps.polls.viewmodel.utils.NetworkMagic;

public class ConnectivityReceiver extends BroadcastReceiver {
    private static final String TAG = Constants.ConnectivityReceiverTAG_;
    private static boolean hasRun = false;

    public ConnectivityReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context != null && NetworkMagic.isOnline(context) && !hasRun){
            hasRun = true;
            Intent unboundService = new Intent(context, UpdateDataService.class);
            context.startService(unboundService);
        }
    }
}
