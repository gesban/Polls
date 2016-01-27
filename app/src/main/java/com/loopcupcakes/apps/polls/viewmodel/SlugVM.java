package com.loopcupcakes.apps.polls.viewmodel;

import android.util.Log;

import com.loopcupcakes.apps.polls.SlugActivity;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

/**
 * Created by evin on 1/26/16.
 */
public class SlugVM {

    private static final String TAG = Constants.SlugVMTAG_;
    private SlugActivity mSlugActivity;

    public SlugVM(SlugActivity slugActivity) {
        mSlugActivity = slugActivity;
        Log.d(TAG, "SlugVM: Hello world");
    }
}
