package com.example.cloud_cast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HourlyObject {
    @SerializedName("dt")
    @Expose
    private String currentTime;

    @SerializedName("temp")
    @Expose
    private String temperature;

    @SerializedName("wind_speed")
    @Expose
    private String windSpeed;

    @SerializedName("weather")
    @Expose
    private List<WeatherObject> weatherObjectList;

    public String getCurrentTime() {
        return currentTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public List<WeatherObject> getWeatherObjectList() {
        return weatherObjectList;
    }
}
