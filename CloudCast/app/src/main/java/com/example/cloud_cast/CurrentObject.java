package com.example.cloud_cast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CurrentObject {
    @SerializedName("dt")
    @Expose
    private String currentTime;

    @SerializedName("sunrise")
    @Expose
    private String sunrise;

    @SerializedName("sunset")
    @Expose
    private String sunset;

    @SerializedName("temp")
    @Expose
    private String temperature;

    @SerializedName("feels_like")
    @Expose
    private String feelsLike;

    @SerializedName("pressure")
    @Expose
    private String pressure;

    @SerializedName("humidity")
    @Expose
    private String humidity;

    @SerializedName("uvi")
    @Expose
    private String uvi;

    @SerializedName("clouds")
    @Expose
    private String cloudiness;

    @SerializedName("wind_speed")
    @Expose
    private String windSpeed;

    @SerializedName("weather")
    @Expose
    private List<WeatherObject> weatherObjectList;

    public CurrentObject() {
        this.currentTime = "N/A";
        this.sunrise = "N/A";
        this.sunset = "N/A";
        this.temperature = "N/A";
        this.feelsLike = "N/A";
        this.humidity = "N/A";
        this.pressure = "N/A";
        this.uvi = "N/A";
        this.cloudiness = "N/A";
        this.windSpeed = "N/A";
        this.weatherObjectList = new ArrayList<>();
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getUvi() {
        return uvi;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public List<WeatherObject> getWeatherObjectList() {
        return weatherObjectList;
    }
}
