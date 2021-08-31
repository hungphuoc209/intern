package com.example.capoocat.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.capoocat.R;
import com.example.capoocat.model.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private final List<Note> mNotes;
    private final Context mContext;
    private final OnNoteClickListener onNoteClickListener;

    public NoteAdapter(List<Note> mNotes, Context mContext, OnNoteClickListener onNoteClickListener) {
        this.mNotes = mNotes;
        this.mContext = mContext;
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(mNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTodo;
        ImageView imgGif;
        CheckBox cbDone;
        GradientDrawable gradientDrawable;
        ConstraintLayout csl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTodo = itemView.findViewById(R.id.tvTodo);
            imgGif = itemView.findViewById(R.id.imgGifItem);
            cbDone = itemView.findViewById(R.id.cbDone);
            csl = itemView.findViewById(R.id.itemViewBg);

            gradientDrawable = (GradientDrawable) csl.getBackground().mutate();

            itemView.setOnClickListener(v -> {
                onNoteClickListener.onItemViewClick(getAdapterPosition());
                notifyItemChanged(getAdapterPosition());
            });

            cbDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
                onNoteClickListener.onDoneClick(getAdapterPosition(), isChecked);
                if (isChecked)
                    tvTodo.setPaintFlags(tvTodo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                else tvTodo.setPaintFlags(tvTodo.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            });

            imgGif.setOnClickListener(v -> onNoteClickListener.onImageViewClick(getAdapterPosition()));
        }

        private void onBind(Note note) {
            tvTodo.setText(note.getTask());
            cbDone.setChecked(note.isDone());
            gradientDrawable.setColor(ContextCompat.getColor(mContext, note.getColor().colorId));
            Glide.with(mContext).load(note.getImgNote().drawableID).into(imgGif);
        }
    }

    public interface OnNoteClickListener {
        void onImageViewClick(int position);

        void onItemViewClick(int position);

        void onDoneClick(int position, boolean isDone);
    }
}
