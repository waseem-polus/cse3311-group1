package com.example.cloud_cast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class SearchWeather extends AppCompatActivity {
    private ArrayList<SearchCityInfo> citiesList;
    private RecyclerView recyclerView;
    //Christopher Nguyen
    //TODO: Make an actual search function (somehow...)
    //TODO: Make the element xml function actually look better
    //TODO: API Wizardry in tandem with the searching
    //TODO: Update my lame deefault profile picture
    //TODO: Finish all the Stormblood, Shadowbringers, and Endwalker Sidequests by the end of the final finals this semester

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_weather);
        recyclerView = findViewById(R.id.SearchWeatherRecyclerView);
        citiesList = new ArrayList<>();

        setCitiesDefault();
        setAdapter();
    }

    private void setAdapter() {
        SearchCityRecyclerAdapter adapter = new SearchCityRecyclerAdapter(this, citiesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void setCitiesDefault(){
        citiesList.add(new SearchCityInfo("Sacramento", "United States", 420.69, 69));
        citiesList.add(new SearchCityInfo("England", "Earth", 777, 77));
        citiesList.add(new SearchCityInfo("Old Sharlayan", "The Northern Empty", 6.2, 2012));

    }
}