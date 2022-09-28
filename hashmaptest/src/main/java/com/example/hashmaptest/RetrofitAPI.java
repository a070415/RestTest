package com.example.hashmaptest;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface RetrofitAPI {

    @GET("api/sample")
    Call<List<Datata>> postOverlapCheck();

    @POST("api/communitytest")
    Call<String> createPost(@Body Datata datata);

    @PUT("api/communitytest/{textid}")
    Call<String> putPost(@Path("textid") String id, @Body Datata datata);

    @DELETE("api/communitytest/{textid}")
    Call<String> deletePost(@Path("textid") String id);

    @Multipart
    @POST("api/upload")
    Call<String> imagePost(@Part MultipartBody.Part uploadFile);

}
