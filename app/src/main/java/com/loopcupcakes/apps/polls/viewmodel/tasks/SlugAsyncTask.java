package com.loopcupcakes.apps.polls.viewmodel.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.viewmodel.interfaces.Pollster;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by evin on 1/26/16.
 */
public class SlugAsyncTask extends AsyncTask<String, Void, Void> {
    private static final String BASE_URL = Constants.BASE_POLLSTER_URL;
    private static final String TAG = Constants.SlugAsyncTaskTAG_;


    @Override
    protected Void doInBackground(String... params) {

        if (params.length < 1){
            return null;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Pollster pollster = retrofit.create(Pollster.class);

        Call<List<Chart>> call = pollster.contributors(params[0]);

        List<Chart> charts;

        try {
            charts = call.execute().body();
            for (Chart chart : charts) {
                System.out.println(chart.getTitle());
            }
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: ", e);
        }

        return null;
    }
}
