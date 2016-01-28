package com.loopcupcakes.apps.polls.view.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Estimate;
import com.loopcupcakes.apps.polls.viewmodel.adapters.CandidateAdapter;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends DialogFragment {

    private static CandidateAdapter mCandidateAdapter;
    private static ArrayList<Estimate> mEstimates;

    static {
        mEstimates = new ArrayList<>();
        mCandidateAdapter = new CandidateAdapter(mEstimates);
    }

    public ChartFragment() {
        // Required empty public constructor
    }

    public static ChartFragment newInstance(Chart chart) {

        Bundle args = new Bundle();
        args.putParcelable(Constants.ChartItemKey, chart);

        ChartFragment fragment = new ChartFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        Chart chart = getArguments().getParcelable(Constants.ChartItemKey);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.f_chart_recycler);

        refreshEstimates(chart);

        recyclerView.setAdapter(mCandidateAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private void refreshEstimates(Chart chart) {
        mEstimates.clear();
        mEstimates.addAll(chart.getEstimates());
    }
}
