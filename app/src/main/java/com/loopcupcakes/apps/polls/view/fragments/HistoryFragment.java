package com.loopcupcakes.apps.polls.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Estimate;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Estimate_;
import com.loopcupcakes.apps.polls.model.entities.huffpost.EstimatesByDate;
import com.loopcupcakes.apps.polls.viewmodel.DetailsVM;
import com.loopcupcakes.apps.polls.viewmodel.tasks.EstimatesAsyncTask;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    private static final String TAG = Constants.HistoryFragmentTAG_;
    // TODO: 1/27/16 call EstimatesAsyncTask before HistoryFragment starts

    private LineChart mLineChart;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLineChart = (LineChart) view.findViewById(R.id.f_history_chart);
        new EstimatesAsyncTask(this).execute(DetailsVM.mChart.getSlug());
    }

    public void buildChart(){
        // TODO: 1/27/16 Do in AsyncTask
        HashMap<String, ArrayList<Entry>> hashMapArrayList = new HashMap<>();
        ArrayList<String> datesArrayList = new ArrayList<>();
        int i = 0;

        for (Estimate estimate : DetailsVM.mChart.getEstimates()){
            hashMapArrayList.put(estimate.getChoice(), new ArrayList<Entry>());
        }

        for (EstimatesByDate estimatesByDate : DetailsVM.mEstimatesByDate){
            String date = estimatesByDate.getDate();
            datesArrayList.add(date);
            for (Estimate_ estimate_ : estimatesByDate.getEstimates()){
                Entry entry = new Entry(estimate_.getValue().floatValue(), i);
                String choice = estimate_.getChoice();
                if (hashMapArrayList.get(choice) != null){
                    hashMapArrayList.get(estimate_.getChoice()).add(entry);
                }
            }
            i++;
        }
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        for (Map.Entry<String, ArrayList<Entry>> entry : hashMapArrayList.entrySet()){
            LineDataSet setComp = new LineDataSet(entry.getValue(), entry.getKey());
            dataSets.add(setComp);
        }

        LineData data = new LineData(datesArrayList, dataSets);
        mLineChart.setData(data);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setDescription("");
        mLineChart.setDrawBorders(false);
        mLineChart.invalidate();
    }
}
