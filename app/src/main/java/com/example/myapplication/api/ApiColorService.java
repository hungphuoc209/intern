package com.example.myapplication.api;

import com.example.myapplication.model.ColorResource;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiColorService {
    @GET("/api/unknown")
    Call<ColorResource> doGetColorResource();
}
