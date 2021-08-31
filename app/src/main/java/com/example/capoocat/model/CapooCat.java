package com.example.capoocat.model;

import com.example.capoocat.R;

public enum CapooCat {
    HAND_CLAP(R.drawable.gif_capoo_handclap),
    HEART_BREAK(R.drawable.gif_capoo_heartbreak),
    LAUGH(R.drawable.gif_capoo_laugh),
    LICK(R.drawable.gif_capoo_lick),
    MONEY(R.drawable.gif_capoo_money),
    SHY(R.drawable.gif_capoo_shy),
    SLEEP(R.drawable.gif_capoo_sleep),
    WASH_HAND(R.drawable.gif_capoo_washhand),
    WOW(R.drawable.gif_capoo_wow),
    REFUSE(R.drawable.gif_cappo_refuse);
    public final int drawableID;

    CapooCat(int drawableID) {
        this.drawableID = drawableID;
    }
}
