package com.loopcupcakes.apps.polls.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopcupcakes.apps.polls.R;
import com.loopcupcakes.apps.polls.viewmodel.MainVM;
import com.loopcupcakes.apps.polls.viewmodel.adapters.TopicAdapter;
import com.loopcupcakes.apps.polls.viewmodel.decorations.SpacesItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static TopicAdapter mTopicAdapter;

    static {
        mTopicAdapter = new TopicAdapter(MainVM.mTopics);
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.f_home_recycler);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(Integer.parseInt(getString(R.string.recycler_home_decoration)));

        recyclerView.setAdapter(mTopicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(spacesItemDecoration);
    }
}
