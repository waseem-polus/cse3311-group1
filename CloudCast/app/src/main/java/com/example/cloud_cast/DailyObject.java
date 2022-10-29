package com.example.cloud_cast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyObject {

    @SerializedName("temp")
    @Expose
    private TempDailyObject temp;

    public TempDailyObject getTemp() {
        return temp;
    }
}
