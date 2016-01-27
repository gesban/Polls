package com.loopcupcakes.apps.polls.viewmodel;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.SlugActivity;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

/**
 * Created by evin on 1/26/16.
 */
public class SlugVM {

    private static final String TAG = Constants.SlugVMTAG_;
    private SlugActivity mSlugActivity;

    private ActionBar mActionBar;

    public SlugVM(SlugActivity slugActivity) {
        mSlugActivity = slugActivity;
    }

    public void initializeLayouts() {
        configureActionBar();
//        retrieveInfo();
    }

    private void configureActionBar() {
        Toolbar toolbar = (Toolbar) mSlugActivity.findViewById(R.id.a_slug_toolbar);

        mSlugActivity.setSupportActionBar(toolbar);
        mActionBar = mSlugActivity.getSupportActionBar();

        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setSubtitle(R.string.subtitle_home);
        }
    }
}
