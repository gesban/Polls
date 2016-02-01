package com.loopcupcakes.apps.polls.viewmodel.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.loopcupcakes.apps.polls.model.entities.parse.Topic;
import com.loopcupcakes.apps.polls.viewmodel.MainVM;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class UpdateDataService extends Service {

    private static final String TAG = Constants.UpdateDataServiceTAG_;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        deleteTopicsLocal();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void deleteTopicsLocal() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Topic");
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    for (ParseObject object : objects){
                        try {
                            object.unpin();
                        } catch (ParseException e1) {
                            Log.e(TAG, "done_unpin: ", e1);
                        }
                    }
                    retrieveTopicsOnline();
                }else {
                    Log.e(TAG, "done: ", e);
                    stopSelf();
                }
            }
        });
    }

    private void retrieveTopicsOnline() {
        // TODO: 1/31/16 Refresh Main Activity items smoothly
        // TODO: 2/1/16 Fix delay time
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Topic");
        query.orderByAscending("priority");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                ArrayList<Topic> topics = new ArrayList<Topic>();
                if (e == null) {
                    for (ParseObject object : objects) {
                        object.pinInBackground();
                        topics.add((Topic) object);
                    }
                    MainVM.mTopics.clear();
                    MainVM.mTopics.addAll(topics);
                    MainVM.mTopicAdapter.notifyDataSetChanged();
                }
                stopSelf();
            }
        });
    }
}
