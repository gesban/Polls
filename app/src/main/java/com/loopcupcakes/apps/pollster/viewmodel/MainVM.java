package com.loopcupcakes.apps.pollster.viewmodel;

import com.loopcupcakes.apps.pollster.MainActivity;
import com.loopcupcakes.apps.pollster.viewmodel.utils.Constants;

/**
 * Created by evin on 1/26/16.
 */
public class MainVM {
    private MainActivity mMainActivity;
    private ParseVM mParseVM;
    private static final String TAG_ = Constants.MainVMTag_;

    public MainVM(MainActivity mainActivity){
        mMainActivity = mainActivity;
        mParseVM = new ParseVM(mMainActivity);
    }

    public void initializeThirdPartyLibraries(){
        mParseVM.initializeParse();
    }

    public void initializeLayouts(){

    }
}
