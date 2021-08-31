package com.example.myapplication.database;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;

public class ColorDatabaseSingleton {
    private static ColorDatabase sColorDatabase;

    private ColorDatabaseSingleton() {
    }

    public static ColorDatabase getColorDatabase(Context context) {
        if (sColorDatabase == null) {
            sColorDatabase = new ColorDatabase(context, DatabaseConstant.NAME_TABLE, null, DatabaseConstant.VERSION);
        }
        return sColorDatabase;
    }
}
