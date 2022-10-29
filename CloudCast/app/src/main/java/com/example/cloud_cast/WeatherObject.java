package com.example.cloud_cast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherObject {
    @SerializedName("id")
    @Expose
    private String objectID;

    @SerializedName("main")
    @Expose
    private String mainDescription;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("icon")
    @Expose
    private String icon;

    public String getObjectID() {
        return objectID;
    }

    public String getMainDescription() {
        return mainDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
