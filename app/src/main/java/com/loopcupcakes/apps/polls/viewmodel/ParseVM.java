package com.loopcupcakes.apps.polls.viewmodel;

import android.content.Context;
import android.util.Log;

import com.loopcupcakes.apps.polls.model.entities.parse.Topic;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;
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

                registerSubclasses();

                Parse.initialize(mContext);
                ParseInstallation.getCurrentInstallation().saveInBackground();
            } catch (Exception e) {
                Log.e(TAG_, e.getMessage());
            }
            sendTestObject();
        }
    }

    private void registerSubclasses() {
        ParseObject.registerSubclass(Topic.class);
    }

    private void sendTestObject() {
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }
}
