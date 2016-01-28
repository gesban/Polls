package com.loopcupcakes.apps.polls.view.fragments;


import android.app.Activity;
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
import android.widget.TextView;

import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Estimate;
import com.loopcupcakes.apps.polls.viewmodel.adapters.CandidateAdapter;
import com.loopcupcakes.apps.polls.viewmodel.decorations.SpacesItemDecoration;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends DialogFragment {

    private static CandidateAdapter mCandidateAdapter;
    private static ArrayList<Estimate> mEstimates;

    private Chart mChart;

    static {
        mEstimates = new ArrayList<>();
        mCandidateAdapter = new CandidateAdapter(mEstimates);
    }

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(Chart chart) {

        Bundle args = new Bundle();
        args.putParcelable(Constants.ChartItemKey, chart);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
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

        mChart = getArguments().getParcelable(Constants.ChartItemKey);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.f_details_recycler);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(Integer.parseInt(getResources().getString(R.string.recycler_home_decoration)));

        refreshEstimates(mChart);

        recyclerView.setAdapter(mCandidateAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(spacesItemDecoration);

        updateTextViews(view);

    }

    private void updateTextViews(View view) {
        Activity activity = getActivity();
        if (activity == null){
            return;
        }

        TextView textViewTitle = (TextView) view.findViewById(R.id.f_details_title_txt);
        textViewTitle.setText(mChart.getTitle());
    }

    private void refreshEstimates(Chart chart) {
        mEstimates.clear();
        mEstimates.addAll(chart.getEstimates());
    }
}
