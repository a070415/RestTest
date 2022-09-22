package com.example.hashmaptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PutActivity extends AppCompatActivity {

    Button btn_put_ok;
    Button btn_put_cancel;
    EditText et_put_title;
    EditText et_put_content;
    EditText et_put_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);

        Intent intent = getIntent();

        btn_put_ok = findViewById(R.id.btn_put_ok);
        btn_put_cancel = findViewById(R.id.btn_put_cancel);
        et_put_title = findViewById(R.id.et_put_title);
        et_put_content = findViewById(R.id.et_put_content);
        et_put_id = findViewById(R.id.et_put_id);

        btn_put_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("et_put_id", et_put_id.getText().toString());
                intent.putExtra("et_put_title", et_put_title.getText().toString());
                intent.putExtra("et_put_content", et_put_content.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn_put_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}