package com.example.hashmaptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    TextView post_title;
    TextView post_content;

    List<Datata> datataList;
//    Datata data = new Datata();

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String title_data = intent.getExtras().getString("title");
        String contnet_data = intent.getExtras().getString("content");

        Call<List<Datata>> call = ApiClient.getApiService().postOverlapCheck();
        call.enqueue(new Callback<List<Datata>>() {
            @Override
            public void onResponse(Call<List<Datata>> call, Response<List<Datata>> response) {
                if(response.isSuccessful()) {

                    datataList = response.body();

                    post_title = findViewById(R.id.post_title);
                    post_content = findViewById(R.id.post_content);

                    post_title.setText(title_data);
                    post_content.setText(contnet_data);
//                    Log.i("jun", data.getContent());

                }
            }

            @Override
            public void onFailure(Call<List<Datata>> call, Throwable t) {

            }
        });

    }
}