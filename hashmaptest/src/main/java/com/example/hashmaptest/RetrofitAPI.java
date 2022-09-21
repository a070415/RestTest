package com.example.hashmaptest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface RetrofitAPI {

    @GET("api/sample")
    Call<List<Datata>> postOverlapCheck();

    @POST("api/communitytest")
    Call<String> createPost(@Body Datata datata);

}
