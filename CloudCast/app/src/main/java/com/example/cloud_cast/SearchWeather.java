package com.example.cloud_cast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchWeather extends AppCompatActivity {
    private ArrayList<SearchCityInfo> citiesList;
    private RecyclerView recyclerView;
    private SearchView searchView;


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
        searchView = findViewById(R.id.searchHeader);
        citiesList = new ArrayList<>();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return false;
            }
        });

        setCitiesDefault();
        setAdapter(citiesList);
    }

    private void filterList(String s) {
        ArrayList<SearchCityInfo> searchCityList = new ArrayList<>();
        for (SearchCityInfo CurrentCity : citiesList){
            if (CurrentCity.getCity().toLowerCase().contains(s.toLowerCase())){
                searchCityList.add(CurrentCity);
            }
        }

        if (searchCityList.isEmpty()){
            Toast.makeText(this, "Nothing came up...", Toast.LENGTH_SHORT).show();
        }
        else{
            setAdapter(searchCityList);
        }
    }

    private void setAdapter( ArrayList<SearchCityInfo> citiesListParameter) {
        SearchCityRecyclerAdapter adapter = new SearchCityRecyclerAdapter(this, citiesListParameter);
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