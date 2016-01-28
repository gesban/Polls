package com.loopcupcakes.apps.polls.viewmodel;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.loopcupcakes.apps.polls.MainActivity;
import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.parse.Topic;
import com.loopcupcakes.apps.polls.view.animations.Animator;
import com.loopcupcakes.apps.polls.viewmodel.adapters.TopicAdapter;
import com.loopcupcakes.apps.polls.viewmodel.decorations.SpacesItemDecoration;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;
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
    private static final String TAG_ = Constants.MainVMTAG_;
    public static ArrayList<Topic> mTopics;
    public static TopicAdapter mTopicAdapter;

    private MainActivity mMainActivity;
    private ParseVM mParseVM;
    private Animator mAnimator;

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
        mParseVM = new ParseVM(mMainActivity);
        mAnimator = new Animator();
    }

    public void initializeThirdPartyLibraries() {
        mParseVM.initializeParse();
    }

    public void initializeLayouts() {
        mDrawerLayout = (DrawerLayout) mMainActivity.findViewById(R.id.a_main_drawer);
        mNavigationView = (NavigationView) mMainActivity.findViewById(R.id.a_main_nav);
        mRecyclerView = (RecyclerView) mMainActivity.findViewById(R.id.a_main_recycler);
        mProgressBar = (ProgressBar) mMainActivity.findViewById(R.id.a_main_progressbar);

        configureActionBar();
        retrieveTopics();
        configureRecycler();
        setupButtons();
    }

    private void setupButtons() {
        ImageButton new_home = (ImageButton) mMainActivity.findViewById(R.id.a_main_hamburger_image);
        new_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    private void configureRecycler() {
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(Integer.parseInt(mMainActivity.getString(R.string.recycler_home_decoration)));

        mRecyclerView.setAdapter(mTopicAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mMainActivity));
        mRecyclerView.addItemDecoration(spacesItemDecoration);
    }

    private void configureActionBar() {
        // TODO: 1/26/16 Add transparent ActionBar
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
        // TODO: 1/26/16 Add menu items
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

    private void retrieveTopics() {
        // TODO: 1/26/16 Update loading TextView if no connection
        // TODO: 1/26/16 Query local datastore first
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Topic");
        query.orderByAscending("priority");
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects != null && objects.size() > 0) {
                    updateTopics(objects);
                    hideProgressBar();
                } else {
                    retrieveTopicsOnline();
                }
            }
        });
    }

    private void retrieveTopicsOnline() {
        // TODO: 1/26/16 Update localstore on connection
        // TODO: 1/28/16 Fix SnackBar parent
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Topic");
        query.orderByAscending("priority");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    updateTopics(objects);
                    hideProgressBar();
                } else {
                    Snackbar snackbar = Snackbar.make(mDrawerLayout, R.string.main_vm_error_internet, Snackbar.LENGTH_LONG);
                    snackbar.show();
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

    private void hideProgressBar(){
        mAnimator.fadeOut(mProgressBar, 500);
    }

}
