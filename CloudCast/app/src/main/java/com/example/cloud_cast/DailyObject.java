package com.example.cloud_cast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DailyObject {
    @SerializedName("dt")
    @Expose
    private String day;

    @SerializedName("temp")
    @Expose
    private TempDailyObject temp;

    @SerializedName("weather")
    @Expose
    private List<WeatherObject> weatherObjectList;

    public DailyObject() {
        this.temp = new TempDailyObject();
        this.weatherObjectList = new ArrayList<>();
    }

    public TempDailyObject getTemp() {
        return temp;
    }

    public List<WeatherObject> getWeatherObjectList() {
        return weatherObjectList;
    }

    public String getDay() {
        return day;
    }
}
