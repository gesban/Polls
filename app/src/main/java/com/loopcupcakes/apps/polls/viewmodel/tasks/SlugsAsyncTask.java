package com.loopcupcakes.apps.polls.viewmodel.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.viewmodel.SlugVM;
import com.loopcupcakes.apps.polls.viewmodel.interfaces.Pollster;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by evin on 1/26/16.
 */
public class SlugsAsyncTask extends AsyncTask<String, Void, List<Chart>> {
    private static final String BASE_URL = Constants.BASE_POLLSTER_URL;
    private static final String TAG = Constants.SlugAsyncTaskTAG_;

    private SlugVM mSlugVM;

    public SlugsAsyncTask(SlugVM slugVM) {
        mSlugVM = slugVM;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        SlugVM.mCharts.clear();
    }

    @Override
    protected List<Chart> doInBackground(String... params) {

        if (params.length < 1) {
            return null;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Pollster pollster = retrofit.create(Pollster.class);

        Call<List<Chart>> call = pollster.slugs(params[0]);

        List<Chart> charts = null;

        try {
            charts = call.execute().body();
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: " + e.getMessage());
        }

        return charts;
    }

    @Override
    protected void onPostExecute(List<Chart> charts) {
        super.onPostExecute(charts);

        if (charts != null){
            Collections.reverse(charts);
            for (Chart chart : charts){
                if (chart.getEstimates() != null && chart.getEstimates().size() > 1){
                    SlugVM.mCharts.add(chart);
                }
            }
            SlugVM.mChartAdapter.notifyDataSetChanged();
            mSlugVM.finishLoading(true);
        }else {
            mSlugVM.finishLoading(false);
        }

    }
}
