package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ColorAdapter;
import com.example.myapplication.database.ColorDatabaseSingleton;
import com.example.myapplication.model.ColorModel;

import java.util.ArrayList;
import java.util.List;

public class LocalColorActivity extends AppCompatActivity {
    private List<ColorModel> mColorModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_local_color_act);
        initData();
        setRvColor(mColorModels);
    }

    private void initData() {
        mColorModels = ColorDatabaseSingleton.getColorDatabase(this).getAll();
    }

    private void setRvColor(List<ColorModel> colorModels) {
        RecyclerView rvLocal = findViewById(R.id.rvLocal);
        ColorAdapter colorAdapter = new ColorAdapter(colorModels);
        LinearLayoutManager lnm = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvLocal.setLayoutManager(lnm);
        rvLocal.setAdapter(colorAdapter);
        colorAdapter.notifyDataSetChanged();
    }
}