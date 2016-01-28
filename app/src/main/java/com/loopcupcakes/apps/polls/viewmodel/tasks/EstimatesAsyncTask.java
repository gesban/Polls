package com.loopcupcakes.apps.polls.viewmodel.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.viewmodel.DetailsVM;
import com.loopcupcakes.apps.polls.viewmodel.adapters.CandidateAdapter;
import com.loopcupcakes.apps.polls.viewmodel.interfaces.Pollster;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by evin on 1/27/16.
 */
public class EstimatesAsyncTask extends AsyncTask<String, Void, Chart> {
    private static final String BASE_URL = Constants.BASE_POLLSTER_URL;
    private static final String TAG = Constants.EstimatesTaskTAG_;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        DetailsVM.mEstimatesByDates.clear();
    }

    @Override
    protected Chart doInBackground(String... params) {

        if (params.length < 1){
            return null;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Pollster pollster = retrofit.create(Pollster.class);

        Call<Chart> call = pollster.estimates(params[0]);

        Chart chart = null;

        try{
            chart = call.execute().body();
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: ", e);
        }

        return chart;
    }

    @Override
    protected void onPostExecute(Chart chart) {
        super.onPostExecute(chart);
        Log.d(TAG, "onPostExecute: " + chart.getEstimatesByDate().size());
//        DetailsVM.mEstimates_.addAll();
    }
}
