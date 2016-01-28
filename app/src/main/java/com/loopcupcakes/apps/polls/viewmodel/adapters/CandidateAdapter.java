package com.loopcupcakes.apps.polls.viewmodel.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Estimate;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

import java.util.List;

/**
 * Created by evin on 1/27/16.
 */
public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {

    private static final String TAG = Constants.CandidateAdapterTAG_;
    private List<Estimate> mEstimates;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewParty;
        public TextView textViewValue;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.recycler_candidate_name_txt);
            textViewParty = (TextView) itemView.findViewById(R.id.recycler_candidate_party_txt);
            textViewValue = (TextView) itemView.findViewById(R.id.recycler_candidate_value_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: " + v.toString());
                }
            });
        }
    }

    public CandidateAdapter(List<Estimate> estimates) {
        mEstimates = estimates;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contactView = inflater.inflate(R.layout.recycler_item_candidate, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Estimate estimate = mEstimates.get(position);
        String strName = (estimate.getFirstName() == null || estimate.getLastName() == null) ?
                estimate.getChoice() :
                estimate.getFirstName() + " " + estimate.getLastName();

        TextView textViewName = holder.textViewName;
        TextView textViewParty = holder.textViewParty;
        TextView textViewValue = holder.textViewValue;

        textViewName.setText(strName);
        textViewParty.setText(estimate.getParty());
        textViewValue.setText(String.valueOf(estimate.getValue()));
    }

    @Override
    public int getItemCount() {
        return mEstimates.size();
    }
}