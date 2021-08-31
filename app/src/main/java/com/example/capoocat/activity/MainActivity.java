package com.example.capoocat.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.capoocat.fragment.SelectGifFragment;
import com.example.capoocat.model.CapooCat;
import com.example.capoocat.model.Note;
import com.example.capoocat.model.NoteColor;
import com.example.capoocat.adapter.NoteAdapter;
import com.example.capoocat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnNoteClickListener, SelectGifFragment.returnCapooCat {
    private ImageButton mImgBtnDelete;
    private final List<Note> mLists = new ArrayList<>();
    private FloatingActionButton mFab;
    private NoteAdapter mNoteAdapter;
    private int mNotePosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mImgBtnDelete = findViewById(R.id.btnDelete);
        mFab = findViewById(R.id.fab);
        ImageView mImgGif = findViewById(R.id.imgGif);
        RecyclerView rvNote = findViewById(R.id.rvNote);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        Glide.with(this).load(R.drawable.gif_capoo_handclap).into(mImgGif);
        mNoteAdapter = new NoteAdapter(mLists, this, this);
        rvNote.setLayoutManager(layoutManager);
        rvNote.setAdapter(mNoteAdapter);
    }

    private void initListener() {
        mFab.setOnClickListener(v -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_custem, null);
            EditText edtAdd = view.findViewById(R.id.edtAdd);
            alert.setView(view);
            alert.setTitle(R.string.add_task_title);
            alert.setMessage(R.string.message);
            alert.setPositiveButton(R.string.ok, (dialog, which) -> mLists.add(new Note(edtAdd.getText().toString(), false, CapooCat.HAND_CLAP, NoteColor.LIGHT_BLUE)));
            alert.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
            alert.show();
        });
        mImgBtnDelete.setOnClickListener(v -> {
            AlertDialog.Builder alertDelete = new AlertDialog.Builder(this);
            alertDelete.setTitle(R.string.ask_delete);
            alertDelete.setMessage(R.string.delete_all);
            alertDelete.setPositiveButton(R.string.ok, (dialog, which) -> {
                mLists.clear();
                mNoteAdapter.notifyDataSetChanged();
            });
            alertDelete.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
            alertDelete.show();
        });
    }

    @Override
    public void onImageViewClick(int position) {
        mNotePosition = position;
        showGifDialogFragment();

    }

    @Override
    public void onItemViewClick(int position) {
        NoteColor color = mLists.get(position).getColor().next();
        mLists.get(position).setColor(color);
    }

    @Override
    public void onDoneClick(int position, boolean isDone) {
        mLists.get(position).setDone(isDone);
    }

    @Override
    public void callBack(CapooCat capooCat) {
        mLists.get(mNotePosition).setImgNote(capooCat);
        mNoteAdapter.notifyDataSetChanged();
    }

    private void showGifDialogFragment() {
        SelectGifFragment selectGifFragment = new SelectGifFragment(this);
        selectGifFragment.show(getSupportFragmentManager(), "dialog");
    }
}
