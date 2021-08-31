package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ColorResource {
    private int page;
    @SerializedName("per_page")
    private int perPage;
    private int total;
    @SerializedName("total_pages")
    private int totalPage;
    List<ColorModel> data = new ArrayList<>();

    public ColorResource(int page, int perPage, int total, int totalPage, List<ColorModel> data) {
        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.totalPage = totalPage;
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ColorModel> getData() {
        return data;
    }

    public void setData(List<ColorModel> data) {
        this.data = data;
    }
}
