package com.example.cloud_cast;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    HomeRecyclerAdapter homeRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;

    DatabaseHelper databaseHelper;

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

    ArrayList<CityObject> favoriteCityList = new ArrayList<>();

    CityObject cityObject = new CityObject();
    CityObject favCityObject;

    private View rootView;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            rootView = inflater.inflate(R.layout.fragment_home, container, false);

        String unit = ((MainActivity) getActivity()).getUnit();

//            This is for tableView in home screen
        cityName = (TextView) rootView.findViewById(R.id.locationNameTextView);
        cityName.setText(cityObject.getCityName());

        temperatureTextView = (TextView) rootView.findViewById(R.id.temperatureTextView);


        try {
            highTempTextView = (TextView) rootView.findViewById(R.id.highTempTextView);
            highTempTextView.setText(cityObject.getDailyObject().get(0).getTemp().getMax() + " °");
            lowTempTextView = (TextView) rootView.findViewById(R.id.lowTempTextView);
            lowTempTextView.setText(cityObject.getDailyObject().get(0).getTemp().getMin() + " °");
        } catch (IndexOutOfBoundsException e) {}


        windSpeedTextView = (TextView) rootView.findViewById(R.id.windSpeedTextView);


        pressureTextView = (TextView) rootView.findViewById(R.id.pressureTextView);
        pressureTextView.setText(cityObject.getCurrentObject().getPressure() +" hPa");

        humidityTextView = (TextView) rootView.findViewById(R.id.humidityTextView);
        humidityTextView.setText(cityObject.getCurrentObject().getHumidity() + " %");

        cloudiness = (TextView) rootView.findViewById(R.id.cloudinessTextView);
        cloudiness.setText(cityObject.getCurrentObject().getCloudiness() + " %");

        feelsLikeTextView = (TextView) rootView.findViewById(R.id.feelsLikeTextView);
        feelsLikeTextView.setText(cityObject.getCurrentObject().getFeelsLike() +" °");

        uviTextView = (TextView) rootView.findViewById(R.id.uviTextView);
        uviTextView.setText(cityObject.getCurrentObject().getUvi() + " uvi");

        if (unit.equals("metric")) {
            temperatureTextView.setText(cityObject.getCurrentObject().getTemperature() + " °C");
            windSpeedTextView.setText(cityObject.getCurrentObject().getWindSpeed() + " m/s");

        } else {
            temperatureTextView.setText(cityObject.getCurrentObject().getTemperature() + " °F");
            windSpeedTextView.setText(cityObject.getCurrentObject().getWindSpeed() + " mph");
        }

        databaseHelper = new DatabaseHelper(getActivity());


//        ArrayList<String> arrayListTest = new ArrayList<>();
//        arrayListTest.add("TUan");
//        arrayListTest.add("Bella");
//        arrayListTest.add("Bell");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.homeRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        homeRecyclerAdapter = new HomeRecyclerAdapter(favoriteCityList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeRecyclerAdapter);

        //displayFavoriteCity();
        //databaseHelper.deleteAll();

        return rootView;
    }

    public void insertFavoriteCity() {
        CityPageFragment cityPageFragment = ((MainActivity) getActivity()).cityPageFragment;
        Boolean checkInsertData = databaseHelper.insert(cityPageFragment.favCityName, cityPageFragment.favLat, cityPageFragment.favLon);
        if (checkInsertData == false) {
            Toast.makeText(getActivity(), "Cannot save favorite city", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Saved favorite city", Toast.LENGTH_LONG).show();
        }
    }

    public void displayFavoriteCity() {
        Cursor cursor = databaseHelper.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "Empty Data", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                while(!cursor.isAfterLast()) {
                    String cityName = cursor.getString(1);
                    String lat = cursor.getString(2);
                    String lon = cursor.getString(3);
                    String unit = ((MainActivity) getActivity()).getUnit();
                    //((MainActivity) getActivity()).getweather2(lat, lon, unit, cityName, "home_fav_city");
                    //favoriteCityList.add(favCityObject);
                    cursor.moveToNext();
                    //TODO: create a fav city object
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void setCityObject(CityObject cityObject) {
        this.cityObject = cityObject;
    }

    public void setFavCityObject(CityObject favCityObject) {
        this.favCityObject = new CityObject();
        this.favCityObject = favCityObject;
    }



}