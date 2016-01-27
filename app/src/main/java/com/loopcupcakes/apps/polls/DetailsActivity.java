package com.loopcupcakes.apps.polls;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.loopcupcakes.apps.polls.viewmodel.DetailsVM;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

public class DetailsActivity extends AppCompatActivity {


    private static final String TAG = Constants.DetailsActivityTAG_;
    private DetailsVM mDetailsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: 1/27/16 Make menu show
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDetailsVM = new DetailsVM(this);
        mDetailsVM.initializeLayout();


        TabLayout tabLayout = (TabLayout) findViewById(R.id.a_main_tab);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));

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

}
