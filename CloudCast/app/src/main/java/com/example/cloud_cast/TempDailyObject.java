package com.example.cloud_cast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempDailyObject {
    @SerializedName("min")
    @Expose
    private String min;

    @SerializedName("max")
    @Expose
    private String max;

    public TempDailyObject() {
        this.min = "N/A";
        this.max = "N/A";
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }
}
