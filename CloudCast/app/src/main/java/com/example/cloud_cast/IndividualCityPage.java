package com.example.cloud_cast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class IndividualCityPage extends AppCompatActivity {

    TextView cityName, temp, tempMinMax, windSpeed, precipitation, humidity, pressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individualcityview);

        // create variables to instantiate each variable of the object city
        cityName = (TextView)findViewById(R.id.cityName);
        temp = (TextView)findViewById(R.id.temp);
        tempMinMax = (TextView)findViewById(R.id.tempMinMax);
        windSpeed = (TextView)findViewById(R.id.windSpeed);
        precipitation = (TextView)findViewById(R.id.precipitation);
        humidity = (TextView)findViewById(R.id.humidity);
        pressure = (TextView)findViewById(R.id.pressure);

        /* Parse through city object to convert each information into a string for print */
        /*
        cityName.setText();
        temp.setText();
        tempMinMax.setText();
        windSpeed.setText();
        precipitation.setText();
        humidity.setText();
        presssure.setText();
         */
    }
}