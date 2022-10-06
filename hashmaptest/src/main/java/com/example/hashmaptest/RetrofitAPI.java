package com.example.hashmaptest;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface RetrofitAPI {

    @GET("api/freeboard")
    Call<List<FreeData>> postOverlapCheck();

    @POST("api/freeboard")
    Call<String> createPost(@Body FreeData data);

//    @PUT("api/communitytest/{textid}")
//    Call<String> putPost(@Path("textid") String id, @Body FreeData data);
//
//    @DELETE("api/communitytest/{textid}")
//    Call<String> deletePost(@Path("textid") String id);

    @Multipart
    @POST("api/boardimgupload")
    Call<List<String>> imagePost(@Part List<MultipartBody.Part> uploadFile);

}
