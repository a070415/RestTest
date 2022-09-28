package com.example.hashmaptest;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CreateActivity extends AppCompatActivity {

    Button btn_ok;
    Button btn_cancel;
    EditText et_title;
    EditText et_content;
    ImageView iv_test;
    Uri currImageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
        iv_test = findViewById(R.id.iv_test);

        Intent intent = getIntent();

        // image 클릭
        iv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //        갤러리 불러오기
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

                startActivityForResult(Intent.createChooser(intent, "select picture"), 5);
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("et_title", et_title.getText().toString());
                intent.putExtra("et_content", et_content.getText().toString());
                intent.putExtra("image_uri", getPathFromUri(currImageURI));
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 5) {
                currImageURI = data.getData();
                Glide.with(getApplicationContext()).load(currImageURI).into(iv_test); //다이얼로그 이미지사진에 넣기
                Log.i("jun", "msg: " + getPathFromUri(currImageURI));

            }
        }
    }

    public String getPathFromUri(Uri uri){
        Cursor cursor = getContentResolver().query(uri, null, null, null, null );
        cursor.moveToNext();
        String path = cursor.getString( cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }
}