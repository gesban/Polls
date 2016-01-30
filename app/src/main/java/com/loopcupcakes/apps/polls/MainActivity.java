package com.loopcupcakes.apps.polls;

import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.loopcupcakes.apps.polls.viewmodel.MainVM;
import com.loopcupcakes.apps.polls.viewmodel.receivers.ConnectivityReceiver;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = Constants.MainActivityTAG_;
    // TODO: 1/26/16 Handle savedInstance
    // TODO: 1/28/16 Menu items
    // TODO: 1/27/16 Handle scrollable toolbar http://android-developers.blogspot.com/2015/05/android-design-support-library.html
    // TODO: 1/28/16 Implement Analytics
    // TODO: 1/28/16 RecyclerView animations
    // TODO: 1/28/16 Fix landscape
    // TODO: 1/28/16 Add image to topics
    // TODO: 1/29/16 Add watch an ad support
    // TODO: 1/29/16 Add LeakCanary support
    // TODO: 1/29/16 Add search feature

    private MainVM mMainVM;
    public ActionBarDrawerToggle mDrawerToggle;
    public ConnectivityReceiver mConnectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainVM = new MainVM(this);
        if (savedInstanceState == null){
            mMainVM.initializeThirdPartyLibraries();
            mMainVM.retrieveTopics();
            mMainVM.initializeUpdater();
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
        mMainVM = null;
        mDrawerToggle = null;
        try {
            unregisterReceiver(mConnectivityReceiver);
        } catch (Exception e) {
            Log.d(TAG, "onDestroy: Couldn't unregister receiver");
        }
    }
}
