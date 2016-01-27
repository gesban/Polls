package com.loopcupcakes.apps.polls.viewmodel;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
//        configureListView();
    }

    private void configureListView() {
        if (mIntent.hasExtra(Constants.ChartItemKey)){
            mChart = mIntent.getParcelableExtra(Constants.ChartItemKey);
        }

        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        ListView listView = (ListView) mDetailsActivity.findViewById(R.id.a_details_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mDetailsActivity,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
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
