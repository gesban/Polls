package com.loopcupcakes.apps.polls.viewmodel.services;

import android.app.IntentService;
import android.content.Intent;

import com.loopcupcakes.apps.polls.model.entities.parse.Topic;
import com.loopcupcakes.apps.polls.viewmodel.MainVM;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class UpdateDataService extends IntentService {

    private static final String TAG = Constants.UpdateDataServiceTAG_;

    public UpdateDataService() {
        super("UpdateDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        retrieveTopicsOnline();
    }

    private void retrieveTopicsOnline() {
        //// TODO: 1/31/16 Refresh Main Activity items smoothly
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
                }
            }
        });
    }
}
