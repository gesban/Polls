package com.loopcupcakes.apps.polls.viewmodel;

import android.content.Intent;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.loopcupcakes.apps.polls.DetailsActivity;
import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Estimate;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Estimate_;
import com.loopcupcakes.apps.polls.model.entities.huffpost.EstimatesByDate;
import com.loopcupcakes.apps.polls.viewmodel.tasks.EstimatesAsyncTask;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by evin on 1/27/16.
 */
public class DetailsVM {
    private static final String TAG = Constants.DetailsVMTAG_;
    DetailsActivity mDetailsActivity;
    Intent mIntent;
    private LineChart mLineChart;

    public static Chart mChart;
    public static ArrayList<EstimatesByDate> mEstimatesByDate;

    static {
        mEstimatesByDate = new ArrayList<>();
    }

    public DetailsVM(DetailsActivity detailsActivity){
        mDetailsActivity = detailsActivity;
        mIntent = mDetailsActivity.getIntent();
    }

    public void initializeLayout() {
        mLineChart = (LineChart) mDetailsActivity.findViewById(R.id.a_details_linechart);

        if (mChart != null){
            final String slug = mChart.getSlug();
            retrieveData(slug);
        }
    }

    private void retrieveData(String slug) {
        new EstimatesAsyncTask(this).execute(slug);
    }

    public void buildChart() {
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

//        Collections.reverse(datesArrayList);

        Log.d(TAG, "buildChart: " + ColorTemplate.COLORFUL_COLORS.length);
        Log.d(TAG, "buildChart: " + ColorTemplate.VORDIPLOM_COLORS.length);
        Log.d(TAG, "buildChart: " + ColorTemplate.JOYFUL_COLORS.length);
        Log.d(TAG, "buildChart: " + ColorTemplate.LIBERTY_COLORS.length);
        Log.d(TAG, "buildChart: " + ColorTemplate.PASTEL_COLORS.length);

        i = 0;
        for (Map.Entry<String, ArrayList<Entry>> entry : hashMapArrayList.entrySet()){
            LineDataSet setComp = new LineDataSet(entry.getValue(), entry.getKey());
            setComp.disableDashedLine();
            setComp.setDrawCircles(false);
            setComp.setLineWidth(4.5f);
            setComp.setColor(ColorTemplate.COLORFUL_COLORS[i++]);
            dataSets.add(setComp);
            if (i > 4)
                break;
//            dataSets.Col
        }

        LineData data = new LineData(datesArrayList, dataSets);
        mLineChart.setData(data);

        mLineChart.setDrawGridBackground(false);
        mLineChart.setDescription("");
        mLineChart.setDrawBorders(false);

        mLineChart.getAxisLeft().setDrawAxisLine(false);
        mLineChart.getAxisLeft().setDrawGridLines(false);
        mLineChart.getAxisRight().setDrawAxisLine(false);
        mLineChart.getAxisRight().setDrawGridLines(false);
        mLineChart.getXAxis().setDrawAxisLine(false);
        mLineChart.getXAxis().setDrawGridLines(false);


        mLineChart.invalidate();
    }
}
