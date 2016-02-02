package com.loopcupcakes.apps.polls.viewmodel;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.loopcupcakes.apps.polls.MainActivity;
import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.parse.Topic;
import com.loopcupcakes.apps.polls.view.animations.Animator;
import com.loopcupcakes.apps.polls.view.fragments.AboutFragment;
import com.loopcupcakes.apps.polls.viewmodel.adapters.TopicAdapter;
import com.loopcupcakes.apps.polls.viewmodel.decorations.SpacesItemDecoration;
import com.loopcupcakes.apps.polls.viewmodel.receivers.ConnectivityReceiver;
import com.loopcupcakes.apps.polls.viewmodel.services.UpdateDataService;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;
import com.loopcupcakes.apps.polls.viewmodel.utils.MessagesMagic;
import com.loopcupcakes.apps.polls.viewmodel.utils.NetworkMagic;
import com.loopcupcakes.apps.polls.viewmodel.utils.ScreenUtils;
import com.loopcupcakes.apps.polls.viewmodel.utils.ShareAppMagic;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evin on 1/26/16.
 */
public class MainVM {
    private static final String TAG = Constants.MainVMTAG_;
    public static ArrayList<Topic> mTopics;
    public static TopicAdapter mTopicAdapter;

    private MainActivity mMainActivity;
    private Animator mAnimator;
    private boolean isReceiverRegistered;

    private ActionBar mActionBar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    static {
        mTopics = new ArrayList<>();
        mTopicAdapter = new TopicAdapter(mTopics);
    }

    public MainVM(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mAnimator = new Animator();

        isReceiverRegistered = false;

        makeLookups();
    }

    private void makeLookups() {
        mDrawerLayout = (DrawerLayout) mMainActivity.findViewById(R.id.a_main_drawer);
        mNavigationView = (NavigationView) mMainActivity.findViewById(R.id.a_main_nav);
        mRecyclerView = (RecyclerView) mMainActivity.findViewById(R.id.a_main_recycler);
        mProgressBar = (ProgressBar) mMainActivity.findViewById(R.id.a_main_progressbar);
    }

    public void initializeLayouts() {
        configureActionBar();
        configureRecycler();
    }

    private void configureRecycler() {
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(Integer.parseInt(mMainActivity.getString(R.string.recycler_home_decoration)));

        mRecyclerView.setAdapter(mTopicAdapter);
        mRecyclerView.addItemDecoration(spacesItemDecoration);

        if (!ScreenUtils.isLandscapeAndLongEnough(mMainActivity)){
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mMainActivity));
        }else{
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        }
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
                    case R.id.nav_about:
                        showAbout();
                        break;
                    case R.id.nav_rate:
                        ShareAppMagic.rateApp(mMainActivity);
                        break;
                    case R.id.nav_like:
                        ShareAppMagic.likeApp(mMainActivity);
                        break;
                    case R.id.nav_more:
                        ShareAppMagic.openMoreApps(mMainActivity);
                        break;
                    case R.id.nav_share:
                        ShareAppMagic.shareApp(mMainActivity);
                        break;
                    default: case R.id.nav_home:
                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    private void showAbout() {
        AboutFragment aboutFragment = new AboutFragment();
        FragmentManager fragmentManager = mMainActivity.getSupportFragmentManager();

        aboutFragment.show(fragmentManager, Constants.AboutFragmentKey);
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

    public void retrieveTopics() {
        // TODO: 2/1/16 Uncomment when updater fixed
        retrieveTopicsOnline();
//        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Topic");
//        query.orderByAscending("priority");
//        query.fromLocalDatastore();
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null && objects != null && objects.size() > 0) {
//                    updateTopics(objects);
//                    hideProgressBar();
//                } else {
//                    retrieveTopicsOnline();
//                }
//            }
//        });
    }

    private void retrieveTopicsOnline() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Topic");
        query.orderByAscending("priority");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    updateTopics(objects);
                    hideProgressBar();
                } else {
                    MessagesMagic.cantConnectMessage(2000, mDrawerLayout, mMainActivity.getString(R.string.check_internet_message));
                }
            }
        });
    }

    private void updateTopics(List<ParseObject> objects) {
        for (ParseObject object : objects) {
            object.pinInBackground();
            mTopics.add((Topic) object);
        }
        mTopicAdapter.notifyDataSetChanged();
    }

    public void hideProgressBar(){
        if (mAnimator != null){
            mAnimator.fadeOut(mProgressBar, 500);
        }else if (mProgressBar != null){
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void initializeUpdater() {
        // TODO: 1/31/16 Make a service initialize it
        mMainActivity.mConnectivityReceiver = new ConnectivityReceiver();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mMainActivity == null){
                    return;
                }
                if (NetworkMagic.isOnline(mMainActivity)) {
                    Intent unboundService = new Intent(mMainActivity, UpdateDataService.class);
                    mMainActivity.startService(unboundService);
                } else {
                    mMainActivity.registerReceiver(mMainActivity.mConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                    isReceiverRegistered = true;
                }
            }
        }, Constants.HandlerDelayInt);
    }

    public boolean isReceiverRegistered() {
        return isReceiverRegistered;
    }

    public void clearReferences() {
        mActionBar = null;
        mRecyclerView = null;
        mMainActivity = null;
        mDrawerLayout = null;
        mNavigationView = null;
        mProgressBar = null;
        mAnimator = null;
    }
}
