package com.loopcupcakes.apps.polls.viewmodel;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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
    private ActionBar mActionBar;
    private TextView mTextViewTitle;

    public static Chart mChart;
    public static ArrayList<EstimatesByDate> mEstimatesByDate;

    static {
        mEstimatesByDate = new ArrayList<>();
    }

    public DetailsVM(DetailsActivity detailsActivity) {
        mDetailsActivity = detailsActivity;
        mIntent = mDetailsActivity.getIntent();
    }

    public void initializeLayout() {
        mLineChart = (LineChart) mDetailsActivity.findViewById(R.id.a_details_linechart);
        mTextViewTitle = (TextView) mDetailsActivity.findViewById(R.id.a_details_title_text);

        setupLineChart();
        configureActionBar();
    }

    private void configureActionBar() {
        Toolbar toolbar = (Toolbar) mDetailsActivity.findViewById(R.id.a_details_toolbar);
        String title = mDetailsActivity.getString(R.string.app_name) + " | " + mDetailsActivity.getString(R.string.a_slug_title);

        mDetailsActivity.setSupportActionBar(toolbar);

        mActionBar = mDetailsActivity.getSupportActionBar();

        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setTitle(title);
            mActionBar.setSubtitle("");
        }

        if (mChart != null) {
            final String stringExtra = mChart.getTitle();

            if (mActionBar != null){
                mActionBar.setSubtitle(stringExtra);
                mTextViewTitle.setText(stringExtra);
            }
        }

    }

    private void setupLineChart() {
        mLineChart = (LineChart) mDetailsActivity.findViewById(R.id.a_details_linechart);

        mLineChart.setDrawGridBackground(false);
        mLineChart.setDescription("");
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

        mLineChart.setAlpha(0.9f);

        mLineChart.setPinchZoom(true);

        Legend l = mLineChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setWordWrapEnabled(true);
    }

    public void retrieveData() {
        if (mChart != null) {
            final String slug = mChart.getSlug();
            if (!SharedPreferencesMagic.isChartFlagTrue(mDetailsActivity.getApplicationContext())) {
                new EstimatesAsyncTask(this, mDetailsActivity.getApplicationContext()).execute(slug);
            } else {
                buildChart();
            }
        }
    }

    public void buildChart() {
        if (mLineChart == null){
            return;
        }

        LinkedHashMap<String, ArrayList<Entry>> LinkedHashMapArrayList = new LinkedHashMap<>();
        ArrayList<String> datesArrayList = new ArrayList<>();

        for (Estimate estimate : DetailsVM.mChart.getEstimates()) {
            LinkedHashMapArrayList.put(estimate.getChoice(), new ArrayList<Entry>());
        }

        buildLineDataSets(LinkedHashMapArrayList, datesArrayList);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        createLineDataSets(LinkedHashMapArrayList, dataSets);

        LineData data = new LineData(datesArrayList, dataSets);

        mLineChart.animateX(2000);
        mLineChart.setData(data);
        mLineChart.invalidate();
    }

    private void createLineDataSets(LinkedHashMap<String, ArrayList<Entry>> LinkedHashMapArrayList, ArrayList<ILineDataSet> dataSets) {
        final float lineWidth = Constants.LineWidthChart;
        int i = 0;
        for (Map.Entry<String, ArrayList<Entry>> entry : LinkedHashMapArrayList.entrySet()) {
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

        for (EstimatesByDate estimatesByDate : DetailsVM.mEstimatesByDate) {
            String date = estimatesByDate.getDate();
            datesArrayList.add(date);
            for (Estimate_ estimate_ : estimatesByDate.getEstimates()) {
                Entry entry = new Entry(estimate_.getValue().floatValue(), i);
                String choice = estimate_.getChoice();
                if (LinkedHashMapArrayList.get(choice) != null) {
                    LinkedHashMapArrayList.get(estimate_.getChoice()).add(entry);
                }
            }
            i++;
        }
    }

    public void clearMemory() {
        mLineChart = null;
        mActionBar = null;
        mTextViewTitle = null;
        mDetailsActivity = null;
        mIntent = null;
    }
}
