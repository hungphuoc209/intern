package com.example.capoocat.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capoocat.R;
import com.example.capoocat.adapter.GifAdapter;
import com.example.capoocat.model.CapooCat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectGifFragment extends DialogFragment implements GifAdapter.onGifClickListener {
    private final List<CapooCat> mCapooCats = new ArrayList<>();
    private final returnCapooCat returnCapooCat;

    public SelectGifFragment(returnCapooCat returnCapooCat) {
        this.returnCapooCat = returnCapooCat;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gif_list_act, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        CapooCat[] capooCats = CapooCat.values();
        mCapooCats.addAll(Arrays.asList(capooCats));
    }

    private void initView(View view) {
        RecyclerView mRvGif = view.findViewById(R.id.rvGif);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false);
        mRvGif.setLayoutManager(gridLayoutManager);
        GifAdapter mGifAdapter = new GifAdapter(mCapooCats, this);
        mRvGif.setAdapter(mGifAdapter);
        mGifAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGifClick(int position) {
        returnCapooCat.callBack(mCapooCats.get(position));
        dismiss();
    }

    public interface returnCapooCat {
        void callBack(CapooCat capooCat);
    }
}
