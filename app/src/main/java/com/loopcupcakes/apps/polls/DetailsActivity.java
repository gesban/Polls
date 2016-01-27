package com.loopcupcakes.apps.polls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.loopcupcakes.apps.polls.viewmodel.DetailsVM;

public class DetailsActivity extends AppCompatActivity {

    private DetailsVM mDetailsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: 1/27/16 Make menu show
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDetailsVM = new DetailsVM(this);
        mDetailsVM.initializeLayout();
    }
}
