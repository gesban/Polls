package com.loopcupcakes.apps.polls.viewmodel;

import android.content.Intent;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.loopcupcakes.apps.polls.DetailsActivity;
import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Estimate;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Estimate_;
import com.loopcupcakes.apps.polls.model.entities.huffpost.EstimatesByDate;
import com.loopcupcakes.apps.polls.viewmodel.tasks.EstimatesAsyncTask;
import com.loopcupcakes.apps.polls.viewmodel.utils.ColorMagic;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;
import com.loopcupcakes.apps.polls.viewmodel.utils.SharedPreferencesMagic;
import com.loopcupcakes.apps.polls.viewmodel.utils.xAxisFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
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

        setupLineChart();

        if (mChart != null){
            final String slug = mChart.getSlug();
            retrieveData(slug);
        }
    }

    private void setupLineChart() {
        mLineChart = (LineChart) mDetailsActivity.findViewById(R.id.a_details_linechart);

        mLineChart.setDrawGridBackground(false);
        mLineChart.setDescription(mChart.getShortTitle());
        mLineChart.setDrawBorders(false);

        mLineChart.getAxisLeft().setDrawAxisLine(false);
        mLineChart.getAxisLeft().setDrawGridLines(false);
        mLineChart.getAxisRight().setDrawAxisLine(false);
        mLineChart.getAxisRight().setDrawGridLines(false);
        mLineChart.getXAxis().setDrawAxisLine(false);
        mLineChart.getXAxis().setDrawGridLines(false);
        mLineChart.getXAxis().setValueFormatter(new xAxisFormatter());

        mLineChart.setTouchEnabled(true);

        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);

        mLineChart.setPinchZoom(false);

        Legend l = mLineChart.getLegend();
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
    }

    private void retrieveData(String slug) {
        if (!SharedPreferencesMagic.isChartFlagTrue(mDetailsActivity.getApplicationContext())){
            new EstimatesAsyncTask(this, mDetailsActivity.getApplicationContext()).execute(slug);
        }else {
            buildChart();
        }
    }

    public void buildChart() {
        LinkedHashMap<String, ArrayList<Entry>> LinkedHashMapArrayList = new LinkedHashMap<>();
        ArrayList<String> datesArrayList = new ArrayList<>();

        for (Estimate estimate : DetailsVM.mChart.getEstimates()){
            LinkedHashMapArrayList.put(estimate.getChoice(), new ArrayList<Entry>());
        }

        buildLineDataSets(LinkedHashMapArrayList, datesArrayList);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        createLineDataSets(LinkedHashMapArrayList, dataSets);

        LineData data = new LineData(datesArrayList, dataSets);

        mLineChart.setData(data);
        mLineChart.invalidate();
    }

    private void createLineDataSets(LinkedHashMap<String, ArrayList<Entry>> LinkedHashMapArrayList, ArrayList<ILineDataSet> dataSets) {
        final float lineWidth = Constants.LineWidthChart;
        int i = 0;
        for (Map.Entry<String, ArrayList<Entry>> entry : LinkedHashMapArrayList.entrySet()){
            LineDataSet setComp = new LineDataSet(entry.getValue(), entry.getKey());

            setComp.setLineWidth(lineWidth);
            setComp.disableDashedLine();
            setComp.setDrawCircles(false);

            int color = ColorMagic.createColor(i++);
            setComp.setColor(color);
            setComp.setCircleColor(color);

            dataSets.add(setComp);
        }
    }

    private void buildLineDataSets(LinkedHashMap<String, ArrayList<Entry>> LinkedHashMapArrayList, ArrayList<String> datesArrayList) {
        int i = 0;

        for (EstimatesByDate estimatesByDate : DetailsVM.mEstimatesByDate){
            String date = estimatesByDate.getDate();
            datesArrayList.add(date);
            for (Estimate_ estimate_ : estimatesByDate.getEstimates()){
                Entry entry = new Entry(estimate_.getValue().floatValue(), i);
                String choice = estimate_.getChoice();
                if (LinkedHashMapArrayList.get(choice) != null){
                    LinkedHashMapArrayList.get(estimate_.getChoice()).add(entry);
                }
            }
            i++;
        }
    }
}
