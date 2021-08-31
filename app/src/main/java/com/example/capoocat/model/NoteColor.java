package com.example.capoocat.model;

import com.example.capoocat.R;

public enum NoteColor {
    PEACH_PINK(R.color.peach_pink),
    LIGHT_PINK(R.color.light_pink),
    LIGHT_GRAY(R.color.light_gray),
    LIGHT_BLUE(R.color.light_blue),
    PASTEL_BLUE(R.color.pastel_blue);

    public final int colorId;

    NoteColor(int colorId) {
        this.colorId = colorId;
    }

    public NoteColor next(){
        NoteColor[] value = values();
        return value[(ordinal()+1) % value.length];
    }
}
