package com.example.hashmaptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView retrofitTextView;

    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitTextView = findViewById(R.id.retrofit_textview);

//        //Retrofit 호출
//        Call<List<Data>> call = ApiClient.getApiService().postOverlapCheck();
//        call.enqueue(new Callback<List<Data>>() {
//            @Override
//            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
//                if(!response.isSuccessful()){
//                    retrofitTextView.setText("연결이 비정상적 : "+ "error code : " + response.code());
//                    return;
//                }
//                retrofitTextView.setText("연결이 성공적 : "+ response.body().toString());
//            }
//            @Override
//            public void onFailure(Call<List<Data>> call, Throwable t) {
//                retrofitTextView.setText("연결실패"+ t.getMessage());
//            }
//        });

        //Retrofit 호출
        Call<List<Datata>> call = ApiClient.getApiService().postOverlapCheck();
        call.enqueue(new Callback<List<Datata>>() {
            @Override
            public void onResponse(Call<List<Datata>> call, Response<List<Datata>> response) {
                if(!response.isSuccessful()){
                    retrofitTextView.setText("연결이 비정상적 : "+ "error code : " + response.code());
                    return;
                }
                retrofitTextView.setText("연결이 성공적 : "+ response.body().toString());
            }
            @Override
            public void onFailure(Call<List<Datata>> call, Throwable t) {
                retrofitTextView.setText("연결실패"+ t.getMessage());
            }
        });

    }
}