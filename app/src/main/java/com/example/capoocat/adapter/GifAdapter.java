package com.example.capoocat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.capoocat.model.CapooCat;
import com.example.capoocat.R;

import java.util.List;

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.ViewHolder> {
    private final List<CapooCat> mCapooCats;
    private final onGifClickListener onGifClickListener;

    public GifAdapter(List<CapooCat> mCapooCats, onGifClickListener onGifClickListener) {
        this.mCapooCats = mCapooCats;
        this.onGifClickListener = onGifClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gif, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(mCapooCats.get(position));
    }

    @Override
    public int getItemCount() {
        return mCapooCats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgGif;

        public ViewHolder(View itemView) {
            super(itemView);
            imgGif = itemView.findViewById(R.id.imgGif);
            itemView.setOnClickListener(v -> onGifClickListener.onGifClick(getAdapterPosition()));
        }

        private void onBind(CapooCat capooCat) {
            Glide.with(itemView.getContext()).load(capooCat.drawableID).into(imgGif);
        }
    }

    public interface onGifClickListener {
        void onGifClick(int position);
    }
}
