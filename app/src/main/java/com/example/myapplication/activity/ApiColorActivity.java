package com.example.myapplication.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ColorAdapter;
import com.example.myapplication.api.ApiColorClient;
import com.example.myapplication.api.ApiColorService;
import com.example.myapplication.database.ColorDatabaseSingleton;
import com.example.myapplication.model.ColorModel;
import com.example.myapplication.model.ColorResource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiColorActivity extends AppCompatActivity {

    private List<ColorModel> mColorModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_api_color_act);

        getDataFromApi();
    }

    private void getDataFromApi() {
        ApiColorService apiService = ApiColorClient.getClient().create(ApiColorService.class);
        Call<ColorResource> colorResourceCall = apiService.doGetColorResource();
        colorResourceCall.enqueue(new Callback<ColorResource>() {
            @Override
            public void onResponse(Call<ColorResource> call, Response<ColorResource> response) {
                Toast.makeText(ApiColorActivity.this, R.string.api_success, Toast.LENGTH_SHORT).show();
                ColorResource colorResource = response.body();
                mColorModels = colorResource.getData();
                ColorDatabaseSingleton.getColorDatabase(ApiColorActivity.this).insertOrReplace(mColorModels);
                setRvColor(mColorModels);
            }

            @Override
            public void onFailure(Call<ColorResource> call, Throwable t) {
                Toast.makeText(ApiColorActivity.this, R.string.api_error, Toast.LENGTH_SHORT).show();
                colorResourceCall.cancel();
            }
        });
    }

    private void setRvColor(List<ColorModel> colorModels) {
        RecyclerView rvApi = findViewById(R.id.rvApi);
        ColorAdapter colorAdapter = new ColorAdapter(colorModels);
        LinearLayoutManager lnm = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvApi.setLayoutManager(lnm);
        rvApi.setAdapter(colorAdapter);
        colorAdapter.notifyDataSetChanged();
    }
}