package com.example.hashmaptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteActivity extends AppCompatActivity {

    Button btn_delete_ok;
    Button btn_delete_cancel;
    EditText et_delete_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent intent = getIntent();

        btn_delete_ok = findViewById(R.id.btn_delete_ok);
        btn_delete_cancel = findViewById(R.id.btn_delete_cancel);
        et_delete_id = findViewById(R.id.et_delete_id);

        btn_delete_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("et_delete_id", et_delete_id.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}