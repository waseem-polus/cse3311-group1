package com.example.cloud_cast;

import static com.example.cloud_cast.MainActivity.apikey;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    HomeRecyclerAdapter homeRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;

    DatabaseHelper databaseHelper;
    Cursor cursor;
    private Retrofit retrofit2 = null;

    private  TextView cityName;
    private  TextView temperatureTextView;
    private  TextView highTempTextView;
    private  TextView lowTempTextView;
    private  TextView windSpeedTextView;
    private  TextView pressureTextView;
    private  TextView humidityTextView;
    private  TextView cloudiness;
    private  TextView feelsLikeTextView;
    private  TextView uviTextView;

    ArrayList<String> cityNameArray;
    ArrayList<String> stateNameArray;
    ArrayList<String> latArray;
    ArrayList<String> lonArray;

    ArrayList<CityObject> favoriteCityList = new ArrayList<>();

    CityObject cityObject = new CityObject();
    CityObject favCityObject;

    String unit;
    private boolean isUpdated = false;

    private View rootView;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unit = ((MainActivity) getActivity()).getUnit();
        databaseHelper = new DatabaseHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            rootView = inflater.inflate(R.layout.fragment_home, container, false);


        if (!cityObject.getLat().equals("N/A")) {
            String unit = ((MainActivity) getActivity()).getUnit();
            cityNameArray = new ArrayList<>();
            stateNameArray = new ArrayList<>();
            latArray = new ArrayList<>();
            lonArray = new ArrayList<>();

//            This is for tableView in home screen
            cityName = (TextView) rootView.findViewById(R.id.locationNameTextView);
            cityName.setText(cityObject.getCityName());

            temperatureTextView = (TextView) rootView.findViewById(R.id.temperatureTextView);


            try {
                highTempTextView = (TextView) rootView.findViewById(R.id.highTempTextView);
                highTempTextView.setText(cityObject.getDailyObject().get(0).getTemp().getMax() + " °");
                lowTempTextView = (TextView) rootView.findViewById(R.id.lowTempTextView);
                lowTempTextView.setText(cityObject.getDailyObject().get(0).getTemp().getMin() + " °");
            } catch (IndexOutOfBoundsException e) {
            }


            windSpeedTextView = (TextView) rootView.findViewById(R.id.windSpeedTextView);


            pressureTextView = (TextView) rootView.findViewById(R.id.pressureTextView);
            pressureTextView.setText(cityObject.getCurrentObject().getPressure() + " hPa");

            humidityTextView = (TextView) rootView.findViewById(R.id.humidityTextView);
            humidityTextView.setText(cityObject.getCurrentObject().getHumidity() + " %");

            cloudiness = (TextView) rootView.findViewById(R.id.cloudinessTextView);
            cloudiness.setText(cityObject.getCurrentObject().getCloudiness() + " %");

            feelsLikeTextView = (TextView) rootView.findViewById(R.id.feelsLikeTextView);
            feelsLikeTextView.setText(cityObject.getCurrentObject().getFeelsLike() + " °");

            uviTextView = (TextView) rootView.findViewById(R.id.uviTextView);
            uviTextView.setText(cityObject.getCurrentObject().getUvi() + " uvi");

            if (unit.equals("metric")) {
                temperatureTextView.setText(cityObject.getCurrentObject().getTemperature() + " °C");
                windSpeedTextView.setText(cityObject.getCurrentObject().getWindSpeed() + " m/s");

            } else {
                temperatureTextView.setText(cityObject.getCurrentObject().getTemperature() + " °F");
                windSpeedTextView.setText(cityObject.getCurrentObject().getWindSpeed() + " mph");
            }

            if (favCityObject != null) {
                insertFavCityList();
            }

            displayFavCityList(isUpdated);

        }

        return rootView;
    }

    public void insertFavCityList() {
        for (int i = 0; i < favoriteCityList.size(); i++) {
            if (favoriteCityList.get(i).getLat().equals(favCityObject.getLat())) {
                Toast.makeText(getActivity(), "Insert Unsuccessfully", Toast.LENGTH_SHORT).show();
                return;
            }
        }
            favoriteCityList.add(favCityObject);
            Boolean checkInsertData = databaseHelper.insertFavCity(favCityObject.getCityName(), favCityObject.getStateName(), favCityObject.getLat(), favCityObject.getLon());
            if (checkInsertData == true) {
                Toast.makeText(getActivity(), "New City Inserted", Toast.LENGTH_SHORT).show();
            }
    }

    //TODO caled from where
    private void displayFavCityList(boolean isUpdated) {
        cursor = databaseHelper.getData();
        //TODO consider this if
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No Entry", Toast.LENGTH_SHORT).show();
            return;
        } else {

            while(cursor.moveToNext()) {
                Log.i("Check cursur first", String.valueOf(cursor.isFirst()));
                cityNameArray.add(cursor.getString(1));
                stateNameArray.add(cursor.getString(2));
                latArray.add(cursor.getString(3));
                lonArray.add(cursor.getString(4));
            }
        }

        for (int i = 0; i < cityNameArray.size(); i++) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/3.0/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weatherapi2 myapi = retrofit2.create(weatherapi2.class);
            Call<CityObject> cityObjectCall = myapi.getweather2(latArray.get(i), lonArray.get(i), unit, apikey);
            int finalI = i;
            cityObjectCall.enqueue(new Callback<CityObject>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<CityObject> call, Response<CityObject> response) {
                    if (response.code() == 404) {
                        Toast.makeText(getContext(), "Invalid latitude and longitude", Toast.LENGTH_LONG).show();
                    } else if (!(response.isSuccessful())) {
                        Log.i("response.notSuccessful", "here");
                        Toast.makeText(getContext(), response.code() + " ", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        favCityObject = response.body();
                        assert favCityObject != null;
                        favCityObject.setCityName(cityNameArray.get(finalI));
                        favCityObject.setStateName(stateNameArray.get(finalI));
                        favoriteCityList.add(favCityObject);
                    }
                            recyclerView = (RecyclerView) rootView.findViewById(R.id.homeRecyclerView);
                            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            homeRecyclerAdapter = new HomeRecyclerAdapter(favoriteCityList);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(homeRecyclerAdapter);
                }

                @Override
                public void onFailure(Call<CityObject> call, Throwable t) {
                    Log.i("Exception", String.valueOf(t.getMessage()));
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }

            });

        }
    }



    public void setCityObject(CityObject cityObject) {
        this.cityObject = cityObject;
    }

    public void setFavCityObject(CityObject favCityObject) {
        this.favCityObject = favCityObject;
    }



}