package com.loopcupcakes.apps.polls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.loopcupcakes.apps.polls.viewmodel.DetailsVM;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

public class DetailsActivity extends AppCompatActivity {


    private static final String TAG = Constants.DetailsActivityTAG_;
    private DetailsVM mDetailsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDetailsVM = new DetailsVM(this);
        mDetailsVM.initializeLayout();
        if (savedInstanceState == null){
            mDetailsVM.retrieveData();
        }else {
            mDetailsVM.buildChart();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDetailsVM.clearMemory();
    }
}
