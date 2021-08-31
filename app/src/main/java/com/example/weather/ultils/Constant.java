package com.example.weather.ultils;

public class Constant {
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static final String ICON_BASE_URL = "https://openweathermap.org/img/wn/";
    public static final String ICON_SUB_PARAM = "@2x.png";
    public static final String API_KEY = "33bc2dcb90af984be651ec3436b23436";
    public static final String QUERY_CITY = "q";
    public static final String QUERY_KEY = "appid";
    public static final String CURRENT_WEATHER = "weather";
    public static final String FORECAST_WEATHER = "forecast";
    public static final String DAILY_WEATHER = "forecast/daily";
    public static final String QUERY_CNT = "cnt";
    public static final long TIME_LIMIT_QUERY = 5 * 1000;
    //database
    public static final String CURRENT_TABLE_NAME = "current";
    public static final String HOURLY_TABLE_NAME = "hourly";
    public static final String DAILY_TABLE_NAME = "daily";
}
