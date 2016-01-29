package com.loopcupcakes.apps.polls.view.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopcupcakes.apps.polls.DetailsActivity;
import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Estimate;
import com.loopcupcakes.apps.polls.viewmodel.adapters.CandidateAdapter;
import com.loopcupcakes.apps.polls.viewmodel.decorations.SpacesItemDecoration;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;
import com.loopcupcakes.apps.polls.viewmodel.utils.TextViewMagic;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends DialogFragment {
    // TODO: 1/29/16 Show snackBar to history
    private static final String TAG = Constants.DetailsFragmentTAG_;
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
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(Integer.parseInt(getResources().getString(R.string.recycler_home_small_decoration)));

        refreshEstimates(mChart);

        recyclerView.setAdapter(mCandidateAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(spacesItemDecoration);

        updateTextViews(view);

        setListeners(view);
    }

    private void setListeners(View view) {
        ImageView imageViewHistory = (ImageView) view.findViewById(R.id.f_details_timeline_btn);
        imageViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        ImageView imageViewLess = (ImageView) view.findViewById(R.id.f_details_up_btn);
        imageViewLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                DetailsFragment f = (DetailsFragment) fm.findFragmentByTag(Constants.ChartFragmentKey);
                if (f != null){
                    ft.remove(f);
                    ft.commit();
                }
            }
        });

        TextView textViewHistorical = (TextView) view.findViewById(R.id.f_details_history_txt);
        textViewHistorical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    private void updateTextViews(View view) {
        TextView textViewTitle = (TextView) view.findViewById(R.id.f_details_title_txt);
        TextView textViewState = (TextView) view.findViewById(R.id.f_details_state_txt);
        TextView textViewDate = (TextView) view.findViewById(R.id.f_details_date_txt);
        TextView textViewElection = (TextView) view.findViewById(R.id.f_details_election_txt);
        TextView textViewCount = (TextView) view.findViewById(R.id.f_details_count_txt);
        TextView textViewUpdated = (TextView) view.findViewById(R.id.f_details_updated_txt);

        textViewTitle.setText(mChart.getTitle());
        textViewState.setText(mChart.getState());
        textViewCount.setText(String.valueOf(mChart.getPollCount()));

        TextViewMagic.formatDate(mChart.getLastUpdated(), textViewUpdated);
        TextViewMagic.validateIfDateSet(mChart.getElectionDate(), textViewDate, textViewElection);
    }

    private void refreshEstimates(Chart chart) {
        mEstimates.clear();
        mEstimates.addAll(chart.getEstimates());
    }
}
