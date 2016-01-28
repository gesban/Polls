package com.loopcupcakes.apps.polls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.loopcupcakes.apps.polls.viewmodel.SlugVM;

public class SlugActivity extends AppCompatActivity {

    private SlugVM mSlugVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: 1/28/16 Fix dates timezone
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slug);

        mSlugVM = new SlugVM(this);
        mSlugVM.initializeLayouts();
    }
}
