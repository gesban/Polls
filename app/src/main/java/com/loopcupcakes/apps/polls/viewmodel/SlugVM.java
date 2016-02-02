package com.loopcupcakes.apps.polls.viewmodel;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.SlugActivity;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.view.animations.Animator;
import com.loopcupcakes.apps.polls.viewmodel.adapters.ChartAdapter;
import com.loopcupcakes.apps.polls.viewmodel.decorations.SpacesItemDecoration;
import com.loopcupcakes.apps.polls.viewmodel.tasks.SlugsAsyncTask;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;
import com.loopcupcakes.apps.polls.viewmodel.utils.MessagesMagic;
import com.loopcupcakes.apps.polls.viewmodel.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evin on 1/26/16.
 */
public class SlugVM {

    private static final String TAG = Constants.SlugVMTAG_;
    private SlugActivity mSlugActivity;
    private Animator mAnimator;

    public static ChartAdapter mChartAdapter;
    public static List<Chart> mCharts;

    private ActionBar mActionBar;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mTextViewTitle;

    private Intent mIntent;

    static {
        mCharts = new ArrayList<>();
        mChartAdapter = new ChartAdapter(mCharts);
    }

    public SlugVM(SlugActivity slugActivity) {
        mSlugActivity = slugActivity;
        mAnimator = new Animator();

        makeLookups();
        getIntent();
    }

    private void getIntent() {
        mIntent = mSlugActivity.getIntent();
    }

    private void makeLookups() {
        mRecyclerView = (RecyclerView) mSlugActivity.findViewById(R.id.a_slug_recycler);
        mProgressBar = (ProgressBar) mSlugActivity.findViewById(R.id.a_slug_progressbar);
        mTextViewTitle = (TextView) mSlugActivity.findViewById(R.id.a_slug_title_text);
    }

    public void initializeLayouts() {
        configureActionBar();
        configureRecyclerView();
    }

    private void configureRecyclerView() {
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(Integer.parseInt(mSlugActivity.getString(R.string.recycler_home_decoration)));

        mRecyclerView.setAdapter(mChartAdapter);
        mRecyclerView.addItemDecoration(spacesItemDecoration);
        if (!ScreenUtils.isLandscapeAndLongEnough(mSlugActivity)) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mSlugActivity));
        }else {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        }
    }

    private void configureActionBar() {
        Toolbar toolbar = (Toolbar) mSlugActivity.findViewById(R.id.a_slug_toolbar);
        String title = mSlugActivity.getString(R.string.app_name) + " | " + mSlugActivity.getString(R.string.a_slug_title);

        mSlugActivity.setSupportActionBar(toolbar);

        mActionBar = mSlugActivity.getSupportActionBar();

        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setTitle(title);
            mActionBar.setSubtitle("");
        }

        if (mIntent.hasExtra(Constants.SlugSubtitleKey)) {
            final String stringExtra = mIntent.getStringExtra(Constants.SlugSubtitleKey);

            mActionBar.setSubtitle(stringExtra);
            mTextViewTitle.setText(stringExtra);
        }

    }

    public void retrieveData() {
        if (mIntent.hasExtra(Constants.SlugNameKey)) {
            String slug = mIntent.getStringExtra(Constants.SlugNameKey);
            new SlugsAsyncTask(this).execute(slug);
        }
    }

    public void finishLoading(boolean status) {
        // TODO: 1/30/16 Refresh on new Internet connection (create Receiver)
        if (status) {
            hideProgressBar();
            if (mChartAdapter.getItemCount() < 1) {
                Snackbar snackbar = Snackbar.make(mRecyclerView, mSlugActivity.getString(R.string.not_enough_data_message), Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        } else {
            MessagesMagic.cantConnectMessage(2000, (View) mRecyclerView.getParent(), mSlugActivity.getString(R.string.check_internet_message));
        }
    }

    public void hideProgressBar() {
        if (mAnimator != null){
            mAnimator.fadeOut(mProgressBar, 500);
        }else if (mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void clearReferences() {
        mRecyclerView = null;
        mActionBar = null;
        mProgressBar = null;
        mTextViewTitle = null;
        mSlugActivity = null;
        mAnimator = null;
    }
}
