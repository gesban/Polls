package com.loopcupcakes.apps.polls.viewmodel.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class UpdateDataService extends IntentService {

    private static final String TAG = Constants.UpdateDataServiceTAG_;

    public UpdateDataService() {
        super("UpdateDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: Entered");
    }
}
