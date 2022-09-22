package com.example.hashmaptest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView retrofitTextView;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<Datata> dataInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycletest);

        dataInfo = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        retrofitTextView = findViewById(R.id.retrofit_textview);



        //Retrofit 호출
        Call<List<Datata>> call = ApiClient.getApiService().postOverlapCheck();
        call.enqueue(new Callback<List<Datata>>() {
            @Override
            public void onResponse(Call<List<Datata>> call, Response<List<Datata>> response) {
//                if(!response.isSuccessful()){
//                    retrofitTextView.setText("연결이 비정상적 : "+ "error code : " + response.code());
//                    return;
//                }
//                retrofitTextView.setText("연결이 성공적 : "+ response.body().toString());

                dataInfo = response.body();

                Log.i("jun", ""+dataInfo);

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), dataInfo);
                recyclerView.setAdapter(recyclerAdapter);
            }
            @Override
            public void onFailure(Call<List<Datata>> call, Throwable t) {
                retrofitTextView.setText("연결실패"+ t.getMessage());
            }
        });

        // 게시판 생성
        findViewById(R.id.btn_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        // 게시판 수정
        findViewById(R.id.btn_put).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PutActivity.class);
                startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == 1) {
                String str_title = data.getStringExtra("et_title");
                String str_content = data.getStringExtra("et_content");

                //         Retrofit post 부분
                Datata input = new Datata(null, str_title, str_content);

                Call<String> post_test = ApiClient.getApiService().createPost(input);
                post_test.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        Log.i("jun", "post 성공");
                        Log.i("jun", "title: " + input.getTitle());
                        Log.i("jun", "content: " + input.getContent());

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.i("jun", "post 실패: " + t.getMessage());
                    }
                });

            }

            else if(requestCode == 2) {
                String str_put_title = data.getStringExtra("et_put_title");
                String str_put_content = data.getStringExtra("et_put_content");
                String str_put_id = data.getStringExtra("et_put_id");

                // put 수정 부분
                Datata da = new Datata(null, str_put_title, str_put_content);

                Call<String> put_call = ApiClient.getApiService().putPost(str_put_id, da);

                put_call.enqueue(new Callback<String>() {
                 @Override
                  public void onResponse(Call<String> call, Response<String> response) {
                     Log.i("jun", "put 성공!");
                   }

                   @Override
                   public void onFailure(Call<String> call, Throwable t) {
                       Log.i("jun", "put 실패: " + t.getMessage());

                    }
               });
            }
        }
    }
}