package com.loopcupcakes.apps.polls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.loopcupcakes.apps.polls.viewmodel.DetailsVM;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

public class DetailsActivity extends AppCompatActivity {


    private static final String TAG = Constants.DetailsActivityTAG_;
    private DetailsVM mDetailsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: 1/27/16 Make menu show
        // TODO: 1/27/16 AsyncTask before this starts to retrieve data
        // TODO: 1/27/16 AsyncTask to fill plot
        // TODO: 1/28/16 Fix rotation saving

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDetailsVM = new DetailsVM(this);
        mDetailsVM.initializeLayout();
    }

}
