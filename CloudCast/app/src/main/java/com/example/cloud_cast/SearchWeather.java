package com.example.cloud_cast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class SearchWeather extends AppCompatActivity {
    private ArrayList<SearchCityInfo> citiesList;
    //Christopher Nguyen
    //TODO: Make an actual search function (somehow...)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_weather);

        setCitiesDefault();
    }

    private void setCitiesDefault(){
        citiesList.add(new SearchCityInfo("Sacramento", "United States", 420.69, 69));
        citiesList.add(new SearchCityInfo("England", "Earth", 777, 77));
        citiesList.add(new SearchCityInfo("Old Sharlayan", "The Northern Empty", 6.2, 2012));

    }
}