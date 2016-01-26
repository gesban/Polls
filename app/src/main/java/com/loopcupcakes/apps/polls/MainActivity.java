package com.loopcupcakes.apps.polls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.loopcupcakes.apps.polls.viewmodel.MainVM;

public class MainActivity extends AppCompatActivity {

    private MainVM mMainVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainVM = new MainVM(this);
        mMainVM.initializeThirdPartyLibraries();
        mMainVM.initializeLayouts();
    }
}
