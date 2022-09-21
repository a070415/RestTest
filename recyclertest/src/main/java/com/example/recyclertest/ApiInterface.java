package com.example.recyclertest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("user/all")
    Call<TestItem> getData();
}
