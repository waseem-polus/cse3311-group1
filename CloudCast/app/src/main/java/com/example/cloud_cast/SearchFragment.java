package com.example.cloud_cast;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {
    private List<SearchCityInfo> citiesList;
    private RecyclerView recyclerView;

    //These 2 api for reference
    //private final static String url2 = "api.openweathermap.org/data/3.0/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}";
    //api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid={API key}
    private final static String apikey = "0ec192d57d6c5e00396f88cd7cad1f6e";

    private Retrofit retrofit1 = null;

    private EditText cityNameEditText;
    private Button searchButton;
    private Button getSelectedButton;

    private Handler handler;

    private String lat;
    private String lon;
    private String unit;
    private String cityName;
    private final String limitCityNumber = String.valueOf(5);
    private CityObject cityObject;

    public SearchFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.searchWeatherRecyclerView);

        cityNameEditText = (EditText) rootView.findViewById(R.id.cityEditText);
        searchButton = (Button) rootView.findViewById(R.id.searchButton);
        getSelectedButton = rootView.findViewById(R.id.saveButton);
        getSelectedButton.setVisibility(View.GONE);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getweather1();
                MainActivity.hideKeyboardFrom(getContext(), rootView);
            }
        });

        setAdapter(citiesList);


        //This button is to handle after user choose a city from api list
        getSelectedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cityPosition = SearchCityRecyclerAdapter.checkedPosition;
              lat = citiesList.get(cityPosition).getLat();
              lon = citiesList.get(cityPosition).getLon();
              unit = ((MainActivity) getActivity()).getUnit();
              cityName = ((MainActivity) getActivity()).getCityNameGPS(lat, lon);
              ((MainActivity) getActivity()).getweather2(lat, lon, unit, cityName);
//              Log.i("lat", lat);
//              Log.i("lon", lon);
//              Log.i("checked", String.valueOf(SearchCityRecyclerAdapter.checkedPosition));

            }
        });



        return rootView;
    }

    private void setAdapter(List<SearchCityInfo> citiesListParameter) {
        SearchCityRecyclerAdapter adapter = new SearchCityRecyclerAdapter(getContext(), (ArrayList<SearchCityInfo>) citiesListParameter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter.setHasStableIds(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        displaySaveButton();
    }


    //This is for first api to retrieve lat and lon by city name
    public void getweather1(){
        retrofit1= new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/geo/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherapi1 myapi = retrofit1.create(weatherapi1.class);

        Call<List<SearchCityInfo>> searchCityInfoCall = myapi.getweather1(cityNameEditText.getText().toString().trim(), limitCityNumber, apikey);
        searchCityInfoCall.enqueue(new Callback<List<SearchCityInfo>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<SearchCityInfo>>call, Response<List<SearchCityInfo>> response) {
                if (response.code() == 404) {
                    Toast.makeText(getContext(), "Please enter a valid city", Toast.LENGTH_LONG).show();
                } else if (!(response.isSuccessful())) {
                    Toast.makeText(getContext(), response.code()+" ", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    citiesList = new ArrayList<>();
                    for (SearchCityInfo searchCityInfo : response.body()){
                        citiesList.add(searchCityInfo);
                    }
                    setAdapter(citiesList);

                }
            }
            @Override
            public void onFailure(Call<List<SearchCityInfo>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }

        });

    }

    public void displaySaveButton() {
        try {
            if (citiesList.size() != 0) {
                getSelectedButton.setVisibility(View.VISIBLE);
            }
        } catch (NullPointerException e) {
            getSelectedButton.setVisibility(View.GONE);
        }
    }

}