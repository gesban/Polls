package com.loopcupcakes.apps.polls;

import android.app.Application;

import com.loopcupcakes.apps.polls.viewmodel.ParseVM;

/**
 * Created by evin on 1/30/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseVM parseVM = new ParseVM(this);
        parseVM.initializeParse();

        //        Fabric.with(mMainActivity, new Crashlytics());
    }

}
