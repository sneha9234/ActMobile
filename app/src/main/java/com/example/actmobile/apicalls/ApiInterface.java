package com.example.actmobile.apicalls;

import com.example.actmobile.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("countries")
    Call<Root> getResults();
}
