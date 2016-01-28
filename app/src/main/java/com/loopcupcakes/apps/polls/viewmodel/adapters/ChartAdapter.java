package com.loopcupcakes.apps.polls.viewmodel.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopcupcakes.apps.polls.DetailsActivity;
import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.viewmodel.tasks.EstimatesAsyncTask;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

import java.util.List;

/**
 * Created by evin on 1/26/16.
 */
public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ViewHolder> {

    private List<Chart> mCharts;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewState;
        public TextView textViewDate;
        public TextView textViewCount;
        public TextView textViewUpdated;
        public Chart chartItem;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.recycler_chart_title_txt);
            textViewState = (TextView) itemView.findViewById(R.id.recycler_chart_state_txt);
            textViewDate = (TextView) itemView.findViewById(R.id.recycler_chart_date_txt);
            textViewCount = (TextView) itemView.findViewById(R.id.recycler_chart_count_txt);
            textViewUpdated = (TextView) itemView.findViewById(R.id.recycler_chart_updated_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                    intent.putExtra(Constants.ChartTitleKey, textViewTitle.getText());
                    intent.putExtra(Constants.ChartItemKey, chartItem);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public ChartAdapter(List<Chart> charts){
        mCharts = charts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

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
        TextView textViewCount = holder.textViewCount;
        TextView textViewUpdated = holder.textViewUpdated;

        textViewTitle.setText(chart.getTitle());
        textViewState.setText(chart.getState());
        textViewDate.setText(chart.getElectionDate());
        textViewCount.setText(String.valueOf(chart.getPollCount()));
        textViewUpdated.setText(chart.getLastUpdated());

        holder.chartItem = chart;
    }

    @Override
    public int getItemCount() {
        return mCharts.size();
    }
}