package com.loopcupcakes.apps.pollster.viewmodel;

import android.content.Context;
import android.util.Log;

import com.loopcupcakes.apps.pollster.viewmodel.utils.Constants;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

/**
 * Created by evin on 1/26/16.
 */
public class ParseVM {
    private static final String TAG_ = Constants.ParseVMTAG_;
    public Context mContext;

    public ParseVM(Context context){
        mContext = context;
    }

    public void initializeParse(){
        if (mContext != null){
            try {
                Parse.enableLocalDatastore(mContext);
                Parse.initialize(mContext);
                ParseInstallation.getCurrentInstallation().saveInBackground();
            } catch (Exception e) {
                Log.e(TAG_, e.getMessage());
            }
            sendTestObject();
        }
    }

    private void sendTestObject() {
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }
}
