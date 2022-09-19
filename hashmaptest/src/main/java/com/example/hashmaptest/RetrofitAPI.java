package com.example.hashmaptest;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;


public interface RetrofitAPI {

//    @GET("user/all")
//    Call<List<Data>> postOverlapCheck();

    @GET("api/sample")
    Call<List<Datata>> postOverlapCheck();

}
