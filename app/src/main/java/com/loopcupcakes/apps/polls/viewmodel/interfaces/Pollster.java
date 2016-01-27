package com.loopcupcakes.apps.polls.viewmodel.interfaces;

import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by evin on 1/26/16.
 */
public interface Pollster {
    @GET("/pollster/api/charts")
    Call<List<Chart>> contributors(@Query("topic") String topic);
}
