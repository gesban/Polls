package com.loopcupcakes.apps.polls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.loopcupcakes.apps.polls.viewmodel.SlugVM;

public class SlugActivity extends AppCompatActivity {

    private SlugVM mSlugVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slug);

        mSlugVM = new SlugVM(this);
        if (savedInstanceState == null){
            mSlugVM.retrieveData();
        }else {
            if (SlugVM.mCharts.size() == 0){
                mSlugVM.retrieveData();
            }else {
                mSlugVM.hideProgressBar();
            }
        }
        mSlugVM.initializeLayouts();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSlugVM.clearReferences();
    }
}
