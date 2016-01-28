package com.loopcupcakes.apps.polls.viewmodel;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.SlugActivity;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.view.animations.Animator;
import com.loopcupcakes.apps.polls.viewmodel.adapters.ChartAdapter;
import com.loopcupcakes.apps.polls.viewmodel.decorations.SpacesItemDecoration;
import com.loopcupcakes.apps.polls.viewmodel.tasks.SlugsAsyncTask;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

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

    static {
        mCharts = new ArrayList<>();
        mChartAdapter = new ChartAdapter(mCharts);
    }

    public SlugVM(SlugActivity slugActivity) {
        mSlugActivity = slugActivity;
        mAnimator = new Animator();
    }

    public void initializeLayouts() {
        mRecyclerView = (RecyclerView) mSlugActivity.findViewById(R.id.a_slug_recycler);
        mProgressBar = (ProgressBar) mSlugActivity.findViewById(R.id.a_slug_progressbar);

        configureActionBar();
        configureRecyclerView();
    }

    private void configureRecyclerView() {
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(Integer.parseInt(mSlugActivity.getString(R.string.recycler_home_decoration)));

        mRecyclerView.setAdapter(mChartAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mSlugActivity));
        mRecyclerView.addItemDecoration(spacesItemDecoration);
    }

    private void configureActionBar() {
        Toolbar toolbar = (Toolbar) mSlugActivity.findViewById(R.id.a_slug_toolbar);
        String title = mSlugActivity.getString(R.string.app_name) + " | " + mSlugActivity.getString(R.string.a_slug_title);
        Intent intent = mSlugActivity.getIntent();

        mSlugActivity.setSupportActionBar(toolbar);

        mActionBar = mSlugActivity.getSupportActionBar();

        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setTitle(title);
            mActionBar.setSubtitle("");
        }

        if (intent.hasExtra(Constants.SlugSubtitleKey)){
            mActionBar.setSubtitle(intent.getStringExtra(Constants.SlugSubtitleKey));
        }

        if (intent.hasExtra(Constants.SlugNameKey)){
            new SlugsAsyncTask(this).execute(intent.getStringExtra(Constants.SlugNameKey));
        }
    }

    public void finishLoading(){
        hideProgressBar();

        if (mChartAdapter.getItemCount() < 1){
            Snackbar snackbar = Snackbar.make(mRecyclerView, mSlugActivity.getString(R.string.a_slug_not_enough_data_message), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public void hideProgressBar() {
        mAnimator.fadeOut(mProgressBar, 500);
    }
}
