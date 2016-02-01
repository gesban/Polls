package com.loopcupcakes.apps.polls;

import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.loopcupcakes.apps.polls.viewmodel.MainVM;
import com.loopcupcakes.apps.polls.viewmodel.receivers.ConnectivityReceiver;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = Constants.MainActivityTAG_;
    // TODO: 1/28/16 Implement Analytics
    // TODO: 1/28/16 Add image to topics
    // TODO: 1/29/16 Add ad-watch support
    // TODO: 1/29/16 Add search feature
    // TODO: 1/30/16 Add share button on candidates list
    // TODO: 1/30/16 Add https://developers.google.com/app-indexing/android/app
    // TODO: 1/30/16 Add onReceive push notification logic
    // TODO: 1/31/16 Ask to be added to https://github.com/wasabeef/recyclerview-animators app-list
    // TODO: 1/31/16 Add animations when an Activity is opened
    // TODO: 1/31/16 Add app invites?

    private MainVM mMainVM;
    public ActionBarDrawerToggle mDrawerToggle;
    public ConnectivityReceiver mConnectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainVM = new MainVM(this);
        if (savedInstanceState == null){
            mMainVM.retrieveTopics();
//            mMainVM.initializeUpdater();
        }else {
            if (MainVM.mTopics.size() == 0){
                mMainVM.retrieveTopics();
            }else {
                mMainVM.hideProgressBar();
            }
        }
        mMainVM.initializeLayouts();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMainVM.isReceiverRegistered()){
            unregisterReceiver(mConnectivityReceiver);
        }
        mMainVM.clearReferences();
        mMainVM = null;
        mDrawerToggle = null;
    }
}
