package com.loopcupcakes.apps.polls.viewmodel;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.loopcupcakes.apps.polls.DetailsActivity;
import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

/**
 * Created by evin on 1/27/16.
 */
public class DetailsVM {
    private static final String TAG = Constants.DetailsVMTAG_;
    DetailsActivity mDetailsActivity;
    ActionBar mActionBar;
    RecyclerView mRecyclerView;
    Intent mIntent;
    Chart mChart;

    public DetailsVM(DetailsActivity detailsActivity){
        mDetailsActivity = detailsActivity;
        mIntent = mDetailsActivity.getIntent();
    }

    public void initializeLayout() {
        configureActionBar();
        configureTabBar();
    }

    private void configureTabBar() {
        TabLayout tabLayout = (TabLayout) mDetailsActivity.findViewById(R.id.a_main_tab);
        tabLayout.addTab(tabLayout.newTab().setText(mDetailsActivity.getString(R.string.a_details_current_tab)));
        tabLayout.addTab(tabLayout.newTab().setText(mDetailsActivity.getString(R.string.a_details_historical_tab)));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "Selected: " + tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "UnSelected: " + tab.getTag());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void configureActionBar() {
        String title = mDetailsActivity.getString(R.string.app_name) + " | " + mDetailsActivity.getString(R.string.a_details_title);
        mActionBar = mDetailsActivity.getSupportActionBar();


        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setTitle(title);
            mActionBar.setSubtitle("");
        }

        if (mIntent.hasExtra(Constants.ChartTitleKey)){
            mActionBar.setSubtitle(mIntent.getStringExtra(Constants.ChartTitleKey));
        }
    }
}
