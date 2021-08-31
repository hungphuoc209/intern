package com.example.myapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.ColorModel;

import java.util.ArrayList;
import java.util.List;

public class ColorDatabase extends SQLiteOpenHelper {
    public ColorDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private void queryData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        database.execSQL(sql);
    }

    private Cursor getData() {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(DatabaseConstant.GET_QUERY + DatabaseConstant.NAME_TABLE, null);
    }

    public void createTable() {
        queryData(DatabaseConstant.CREATE_TABLE_QUERY);
    }

    public void insertOrReplace(List<ColorModel> colorModels) {
        for (ColorModel colorModel : colorModels) {
            int id = colorModel.getId();
            int year = colorModel.getYear();
            String name = colorModel.getName();
            String color = colorModel.getColor();
            String pantoneValue = colorModel.getPantoneValue();
            queryData(DatabaseConstant.INSERT_OR_REPLACE_QUERY + id + "," + year + ",'" + name + "','" + color + "','" + pantoneValue + "')");
        }
    }

    public List<ColorModel> getAll() {
        List<ColorModel> colorModels = new ArrayList<>();
        Cursor dataColorCursor = getData();
        while (dataColorCursor.moveToNext()) {
            int id = dataColorCursor.getInt(0);
            int year = dataColorCursor.getInt(1);
            String name = dataColorCursor.getString(2);
            String color = dataColorCursor.getString(3);
            String pantoneValue = dataColorCursor.getString(4);
            colorModels.add(new ColorModel(id, name, year, color, pantoneValue));
        }
        return colorModels;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
