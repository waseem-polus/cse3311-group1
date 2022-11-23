package com.example.cloud_cast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CityObject {
    @SerializedName("lat")
    @Expose
    private String lat;

    @SerializedName("lon")
    @Expose
    private String lon;


    @SerializedName("current")
    @Expose
    private CurrentObject currentObject;

    @SerializedName("hourly")
    @Expose
    private List<HourlyObject> hourlyObject;

    @SerializedName("daily")
    @Expose
    private List<DailyObject> dailyObject;

    private String cityName;

    private String stateName;

    public CityObject() {
        this.lat = "N/A";
        this.lon = "N/A";
        this.currentObject = new CurrentObject();
        this.hourlyObject = new ArrayList<>();
        this.dailyObject = new ArrayList<>();

    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public CurrentObject getCurrentObject() {
        return currentObject;
    }

    public List<HourlyObject> getHourlyObject() {
        return hourlyObject;
    }

    public List<DailyObject> getDailyObject() {
        return dailyObject;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
