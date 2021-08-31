package com.example.myapplication.database;

public class DatabaseConstant {
    public static final String CREATE_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS color(id INTEGER PRIMARY KEY, year INTEGER, name VARCHAR(50), color VARCHAR(10),pantoneValue VARCHAR(50))";
    public static final String INSERT_OR_REPLACE_QUERY =
            "INSERT OR REPLACE INTO color VALUES(";
    public static final String NAME_TABLE = "color";
    public static final String GET_QUERY = "SELECT * FROM ";
    public static final int VERSION = 1;
}
