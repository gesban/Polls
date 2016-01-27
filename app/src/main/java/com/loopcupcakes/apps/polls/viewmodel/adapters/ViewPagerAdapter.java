package com.loopcupcakes.apps.polls.viewmodel.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.loopcupcakes.apps.polls.view.fragments.ChartFragment;
import com.loopcupcakes.apps.polls.view.fragments.HistoryFragment;

/**
 * Created by evin on 1/27/16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ChartFragment();
            default:
                return new HistoryFragment();
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
