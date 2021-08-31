package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.ColorModel;
import com.example.myapplication.model.ColorResource;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
    List<ColorModel> colorModelList;

    public ColorAdapter(List<ColorModel> colorModelList) {
        this.colorModelList = colorModelList;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_item_view, parent, false);
        return new ColorViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return colorModelList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        holder.onBind(colorModelList.get(position));
    }

    public static class ColorViewHolder extends RecyclerView.ViewHolder {
        ImageView imgColor;
        TextView tvId, tvColor, tvYear, tvPantoneValue, tvName;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgColor = itemView.findViewById(R.id.imgColor);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvColor = itemView.findViewById(R.id.tvColor);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvPantoneValue = itemView.findViewById(R.id.tvPantoneValue);
        }

        public void onBind(ColorModel colorModel) {
            tvId.setText(String.valueOf(colorModel.getId()));
            tvName.setText(colorModel.getName());
            tvColor.setText(colorModel.getColor());
            tvYear.setText(String.valueOf(colorModel.getYear()));
            tvPantoneValue.setText(colorModel.getPantoneValue());
            imgColor.setBackgroundColor(Color.parseColor(tvColor.getText().toString()));
        }
    }
}
