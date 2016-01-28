package com.loopcupcakes.apps.polls.viewmodel.adapters;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.SlugActivity;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.view.fragments.DetailsFragment;
import com.loopcupcakes.apps.polls.viewmodel.DetailsVM;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;
import com.loopcupcakes.apps.polls.viewmodel.utils.TextViewMagic;

import java.util.List;

/**
 * Created by evin on 1/26/16.
 */
public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ViewHolder> {

    private static final String TAG = Constants.ChartAdapterTAG_;
    private List<Chart> mCharts;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewState;
        public TextView textViewElection;
        public TextView textViewDate;
        public TextView textViewCount;
        public TextView textViewUpdated;
        public TextView textViewWinner;
        public TextView textViewLoser;
        public TextView textViewWinnerValue;
        public TextView textViewLoserValue;
        public Chart chartItem;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.recycler_chart_title_txt);
            textViewState = (TextView) itemView.findViewById(R.id.recycler_chart_state_txt);
            textViewDate = (TextView) itemView.findViewById(R.id.recycler_chart_date_txt);
            textViewElection = (TextView) itemView.findViewById(R.id.recycler_chart_election_txt);
            textViewCount = (TextView) itemView.findViewById(R.id.recycler_chart_count_txt);
            textViewUpdated = (TextView) itemView.findViewById(R.id.recycler_chart_updated_txt);
            textViewWinner = (TextView) itemView.findViewById(R.id.recycler_chart_winner_name_txt);
            textViewLoser = (TextView) itemView.findViewById(R.id.recycler_chart_loser_name_txt);
            textViewWinnerValue = (TextView) itemView.findViewById(R.id.recycler_chart_winner_value_txt);
            textViewLoserValue = (TextView) itemView.findViewById(R.id.recycler_chart_loser_value_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SlugActivity slugActivity = (SlugActivity) v.getContext();
                    DetailsFragment detailsFragment = DetailsFragment.newInstance(chartItem);

                    FragmentTransaction fragmentTransaction = slugActivity.getSupportFragmentManager().beginTransaction();
                    detailsFragment.show(fragmentTransaction, Constants.ChartFragmentKey);

                    DetailsVM.mChart = chartItem;
                }
            });
        }
    }

    public ChartAdapter(List<Chart> charts){
        mCharts = charts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contactView = inflater.inflate(R.layout.recycler_item_chart, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chart chart = mCharts.get(position);

        TextView textViewTitle = holder.textViewTitle;
        TextView textViewState = holder.textViewState;
        TextView textViewDate = holder.textViewDate;
        TextView textViewElection = holder.textViewElection;
        TextView textViewCount = holder.textViewCount;
        TextView textViewUpdated = holder.textViewUpdated;
        TextView textViewWinner = holder.textViewWinner;
        TextView textViewLoser = holder.textViewLoser;
        TextView textViewWinnerValue = holder.textViewWinnerValue;
        TextView textViewLoserValue = holder.textViewLoserValue;

        textViewTitle.setText(chart.getTitle());
        textViewState.setText(chart.getState());
        textViewCount.setText(String.valueOf(chart.getPollCount()));

        TextViewMagic.validateIfDateSet(chart.getElectionDate(), textViewDate, textViewElection);
        TextViewMagic.formatDate(chart.getLastUpdated(), textViewUpdated);
        TextViewMagic.setWinnerLoser(textViewWinner, textViewLoser, textViewWinnerValue, textViewLoserValue, chart);

        holder.chartItem = chart;
    }

    @Override
    public int getItemCount() {
        return mCharts.size();
    }
}