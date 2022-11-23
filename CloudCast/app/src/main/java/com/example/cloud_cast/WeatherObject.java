package com.example.cloud_cast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherObject {

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("icon")
    @Expose
    private String icon;

    public WeatherObject() {
        this.description = "N/A";
        this.icon = "N/A";
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
