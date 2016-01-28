package com.loopcupcakes.apps.polls;

import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.loopcupcakes.apps.polls.viewmodel.MainVM;

public class MainActivity extends AppCompatActivity {
    // TODO: 1/26/16 Handle savedInstance
    // TODO: 1/28/16 Menu items
    // TODO: 1/27/16 Remove Loading Fragment and set a ProgressBar above
    // TODO: 1/27/16 Handle scrollable toolbar http://android-developers.blogspot.com/2015/05/android-design-support-library.html
    // TODO: 1/27/16 Implement NestedScrollView http://developer.android.com/reference/android/support/design/widget/AppBarLayout.html
    // TODO: 1/28/16 Implement Crashalytics
    // TODO: 1/28/16 Implement Analytics
    // TODO: 1/28/16 RecyclerView animations
    // TODO: 1/28/16 Set nice icon
    // TODO: 1/28/16 Check if Internet connection available
    // TODO: 1/28/16 Fix landscape
    // TODO: 1/28/16 Add image to topics

    private MainVM mMainVM;
    public ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainVM = new MainVM(this);
        mMainVM.initializeThirdPartyLibraries();
        mMainVM.initializeLayouts();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
