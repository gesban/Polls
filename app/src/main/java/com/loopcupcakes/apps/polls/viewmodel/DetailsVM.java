package com.loopcupcakes.apps.polls.viewmodel;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;

import com.loopcupcakes.apps.polls.DetailsActivity;
import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.viewmodel.adapters.ViewPagerAdapter;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

/**
 * Created by evin on 1/27/16.
 */
public class DetailsVM {
    private static final String TAG = Constants.DetailsVMTAG_;
    DetailsActivity mDetailsActivity;
    ActionBar mActionBar;
    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;
    TabLayout mTabLayout;
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
        configurePager();
    }

    private void configurePager() {
        mViewPager = (ViewPager) mDetailsActivity.findViewById(R.id.a_details_pager);
        mViewPagerAdapter = new ViewPagerAdapter(mDetailsActivity.getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    private void configureTabBar() {
        mTabLayout = (TabLayout) mDetailsActivity.findViewById(R.id.a_details_tab);
        mTabLayout.addTab(mTabLayout.newTab().setText(mDetailsActivity.getString(R.string.a_details_current_tab)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mDetailsActivity.getString(R.string.a_details_historical_tab)));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
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
