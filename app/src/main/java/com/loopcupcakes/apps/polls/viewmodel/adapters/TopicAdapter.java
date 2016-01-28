package com.loopcupcakes.apps.polls.viewmodel.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.SlugActivity;
import com.loopcupcakes.apps.polls.model.entities.parse.Topic;
import com.loopcupcakes.apps.polls.viewmodel.utils.Constants;

import java.util.List;

/**
 * Created by evin on 1/26/16.
 */
public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private List<Topic> mTopics;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewDescription;
        public TextView textViewDelimiter;
        public TextView textViewYear;
        public String slug;
        public String subtitle;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewDescription = (TextView) itemView.findViewById(R.id.recycler_topic_description_txt);
            textViewDelimiter = (TextView) itemView.findViewById(R.id.recycler_topic_delimiter_txt);
            textViewYear = (TextView) itemView.findViewById(R.id.recycler_topic_year_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SlugActivity.class);
                    intent.putExtra(Constants.SlugSubtitleKey, subtitle);
                    intent.putExtra(Constants.SlugNameKey, slug);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public TopicAdapter(List<Topic> topics){
        mTopics = topics;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contactView = inflater.inflate(R.layout.recycler_item_topic, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Topic topic = mTopics.get(position);
        String subtitle = topic.getDescription() + " " + topic.getYear() + ". " + topic.getDelimiter() + ".";

        TextView textViewDescription = holder.textViewDescription;
        TextView textViewDelimiter = holder.textViewDelimiter;
        TextView textViewYear = holder.textViewYear;

        textViewDescription.setText(topic.getDescription());
        textViewDelimiter.setText(topic.getDelimiter());
        textViewYear.setText(String.valueOf(topic.getYear()));

        holder.slug = topic.getName();
        holder.subtitle = subtitle;
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }
}
