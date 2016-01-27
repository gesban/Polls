package com.loopcupcakes.apps.polls.viewmodel;


import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.loopcupcakes.apps.polls.MainActivity;
import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

/**
 * Created by evin on 1/26/16.
 */
public class MainVM {
    private static final String TAG_ = Constants.MainVMTag_;

    private MainActivity mMainActivity;
    private ParseVM mParseVM;

    private ActionBar mActionBar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    public MainVM(MainActivity mainActivity){
        mMainActivity = mainActivity;
        mParseVM = new ParseVM(mMainActivity);
    }

    public void initializeThirdPartyLibraries(){
        mParseVM.initializeParse();
    }

    public void initializeLayouts(){

        mDrawerLayout = (DrawerLayout) mMainActivity.findViewById(R.id.a_main_drawer);
        mNavigationView = (NavigationView) mMainActivity.findViewById(R.id.a_main_nav);

        configureActionBar();
    }

    private void configureActionBar() {
        Toolbar toolbar = (Toolbar) mMainActivity.findViewById(R.id.a_main_toolbar);

        mMainActivity.setSupportActionBar(toolbar);
        mActionBar = mMainActivity.getSupportActionBar();

        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setSubtitle(R.string.subtitle_home);
        }

        mMainActivity.mDrawerToggle = getActionBarDrawerToggle();

        mDrawerLayout.setDrawerListener(mMainActivity.mDrawerToggle);
        mMainActivity.mDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(getNavigationListener());
    }

    private NavigationView.OnNavigationItemSelectedListener getNavigationListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    default:
                        Log.d(TAG_, "Item selected");
                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    private ActionBarDrawerToggle getActionBarDrawerToggle() {
        return new ActionBarDrawerToggle(mMainActivity, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mMainActivity.invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mMainActivity.invalidateOptionsMenu();
            }
        };
    }
}
