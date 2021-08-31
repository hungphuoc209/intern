package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.ColorDatabaseSingleton;

public class MainActivity extends AppCompatActivity {
    private Button mBtnGetApi, mBtnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
        initView();
        initListeners();
    }

    private void initDatabase() {
        ColorDatabaseSingleton.getColorDatabase(this).createTable();
    }

    private void initListeners() {
        mBtnGetApi.setOnClickListener(v -> {
            Intent apiIntent = new Intent(this, ApiColorActivity.class);
            startActivity(apiIntent);
        });
        mBtnShow.setOnClickListener(v -> {
            Intent localIntent = new Intent(this, LocalColorActivity.class);
            startActivity(localIntent);
        });
    }

    private void initView() {
        mBtnGetApi = findViewById(R.id.btnGetAPI);
        mBtnShow = findViewById(R.id.btnShow);
    }
}