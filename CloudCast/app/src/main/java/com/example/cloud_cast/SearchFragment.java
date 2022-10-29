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
    public static CityObject cityObject;
    private RecyclerView recyclerView;

    //These 2 api for reference
    //private final static String url1 = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}" ;
    //private final static String url2 = "api.openweathermap.org/data/3.0/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}";
    private final static String apikey = "0ec192d57d6c5e00396f88cd7cad1f6e";
    //api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid={API key}

    private Retrofit retrofit1 = null;
    private Retrofit retrofit2 = null;


    private EditText cityNameEditText;
    private Button searchButton;
    private Button getSelectedButton;

    private Handler handler;

    String lat;
    String lon;
    private final String limitCityNumber = String.valueOf(5);

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
                getweather1(null);

                MainActivity.hideKeyboardFrom(getContext(), rootView);


                //This part is to handle save button in search screen
                //Delay the button, so api info will be retrieved before the button
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (citiesList.size() != 0) {
                                getSelectedButton.setVisibility(View.VISIBLE);
                            }
                        } catch (NullPointerException e) {
                            getSelectedButton.setVisibility(View.GONE);
                        }
                    }
                }, 500);
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
              getweather2(null);
              Log.i("lat", lat);
              Log.i("lon", lon);
              Log.i("checked", String.valueOf(SearchCityRecyclerAdapter.checkedPosition));

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

    }


    //This is for first api to retrieve lat and lon by city name
    public void getweather1(View v){
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
                    Log.i("err", "it heres");
                    Toast.makeText(getContext(), response.code()+" ", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    citiesList = new ArrayList<>();
                    for (SearchCityInfo searchCityInfo : response.body()){
                        citiesList.add(searchCityInfo);
                    }
                    assert citiesList != null;
                    setAdapter(citiesList);

                }
            }

            @Override
            public void onFailure(Call<List<SearchCityInfo>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }

        });

    }

//    This is for second api to retrieve all information
    public void getweather2(View v){
        retrofit2 = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/3.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherapi2 myapi=retrofit2.create(weatherapi2.class);
        Call<CityObject> cityObjectCall=myapi.getweather2(lat, lon, apikey);
        cityObjectCall.enqueue(new Callback<CityObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<CityObject>call, Response<CityObject>response) {
                if (response.code() == 404) {
                    Toast.makeText(getContext(), "Invalid latitude and longitude", Toast.LENGTH_LONG).show();
                } else if (!(response.isSuccessful())) {
                    Toast.makeText(getContext(), response.code()+" ", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    cityObject = new CityObject();
                    cityObject = response.body();
                    cityObject.setCityName(cityNameEditText.getText().toString().trim());
//                    Log.i("log", "log");
                }
            }

            @Override
            public void onFailure(Call<CityObject> call, Throwable t) {
                Log.i("Exception", String.valueOf(t.getMessage()));
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }

        });

    }

//    public CityObject getCityObject() {
//        return cityObject;
//    }
}