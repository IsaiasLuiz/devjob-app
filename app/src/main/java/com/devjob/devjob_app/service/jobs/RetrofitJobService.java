package com.devjob.devjob_app.service.jobs;

import com.devjob.devjob_app.model.Resource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitJobService {

    @GET("/graphql")
    Call<Resource> get(@Query(value = "query", encoded = true) String query);
}
