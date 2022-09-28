package com.example.hashmaptest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView retrofitTextView;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<Datata> dataInfo;
    Uri currImageURI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycletest);

        dataInfo = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


//        Retrofit 호출
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

                Log.i("jun", "" + dataInfo);

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), dataInfo);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<Datata>> call, Throwable t) {
            }
        });


//         게시판 생성
        findViewById(R.id.btn_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        // 게시판 수정
//        findViewById(R.id.btn_put).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, PutActivity.class);
//                startActivityForResult(intent, 2);
//            }
//        });

        // 게시판 삭제
        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                startActivityForResult(intent, 3);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                String str_title = data.getStringExtra("et_title");
                String str_content = data.getStringExtra("et_content");
                String str_uri = data.getStringExtra("image_uri");

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

                // image 파일 보내기
                File file = new File(str_uri);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                Log.i("test", "insertPromote: " + file.getName());
                Log.i("test", "insertPromote: " + requestFile.contentType());
                Log.i("test", "insertPromote: " + uploadFile.body());

                Call<String> image_call = ApiClient.getApiService().imagePost(uploadFile);
                image_call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("test", "성공");

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.i("test", "onFailure: " + t.getMessage());

                    }
                });



            } else if (requestCode == 2) {
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
            } else if (requestCode == 3) {
                String str_delete_id = data.getStringExtra("et_delete_id");

                // delete 삭제할 부분

                Call<String> delete_call = ApiClient.getApiService().deletePost(str_delete_id);

                delete_call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("jun", "delete 성공!");
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.i("jun", "delete 실패: " + t.getMessage());
                    }
                });
//            } else if (requestCode == 5) {
//                currImageURI = data.getData();
//                Glide.with(getApplicationContext()).load(currImageURI).into(iv_test); //다이얼로그 이미지사진에 넣기
//                Log.i("jun", "msg: " + getPathFromUri(currImageURI));


            }
        }
    }

    //핸드폰 갤러리에 있는 사진의 uri 를 통해 경로를 얻는 것.
    public String getPathFromUri(Uri uri){
        Cursor cursor = getContentResolver().query(uri, null, null, null, null );
        cursor.moveToNext();
        String path = cursor.getString( cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

}