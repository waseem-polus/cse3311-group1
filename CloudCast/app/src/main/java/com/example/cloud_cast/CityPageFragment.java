package com.example.cloud_cast;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CityPageFragment extends Fragment {
    BottomNavigationView bottomNavigationView;
    LinearLayoutManager linearLayoutManager;
    CitypageRecyclerAdapter citypageRecyclerAdapter;
    ArrayList<DailyObject> dailyObjects;
    CityObject cityObject = new CityObject();

    private  TextView currentDate;
    private  TextView cityName;
    private  TextView description;
    private  TextView sunrise;
    private  TextView sunset;
    private  TextView temperatureTextView;
    private  TextView highTempTextView;
    private  TextView lowTempTextView;
    private  TextView windSpeedTextView;
    private  TextView pressureTextView;
    private  TextView humidityTextView;
    private  TextView cloudiness;
    private  TextView feelsLikeTextView;
    private  TextView uviTextView;
    private ImageView currentIcon;
    private String iconCode;
    private ImageView backArrowImageView;
    private Button saveButton;

    private String favCityName;
    private String favState;
    private String favLat;
    private String favLon;

    DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());

    public CityPageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_city_page, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.dailyRecyclerView);

        String unit = ((MainActivity) getActivity()).getUnit();


        //            This is for tableView in citypage screen
        currentDate = (TextView) rootView.findViewById(R.id.dateTextView);
        currentDate.setText(convertTimeStamp(cityObject.getCurrentObject().getCurrentTime(), true));

        sunrise = (TextView) rootView.findViewById(R.id.sunriseTextView);
        sunrise.setText(cityObject.getCurrentObject().getSunrise());

        sunset = (TextView) rootView.findViewById(R.id.sunsetTextView);
        sunset.setText(cityObject.getCurrentObject().getSunset());

        cityName = (TextView) rootView.findViewById(R.id.cityNameTextView);
        cityName.setText(cityObject.getCityName());

        temperatureTextView = (TextView) rootView.findViewById(R.id.tempTextView);


        try {
            highTempTextView = (TextView) rootView.findViewById(R.id.highTempTextView);
            highTempTextView.setText(cityObject.getDailyObject().get(0).getTemp().getMax() + " °");
            lowTempTextView = (TextView) rootView.findViewById(R.id.lowTempTextView);
            lowTempTextView.setText(cityObject.getDailyObject().get(0).getTemp().getMin() + " °");

            description = (TextView) rootView.findViewById(R.id.descriptionTextView);
            description.setText(cityObject.getCurrentObject().getWeatherObjectList().get(0).getDescription());

            iconCode = cityObject.getCurrentObject().getWeatherObjectList().get(0).getIcon();
        } catch (IndexOutOfBoundsException e) {}

        currentIcon = (ImageView) rootView.findViewById(R.id.currentIcon);
        Picasso.get().load("https://openweathermap.org/img/wn/" + iconCode + ".png").into(currentIcon);

        windSpeedTextView = (TextView) rootView.findViewById(R.id.windSpeedTextView);


        pressureTextView = (TextView) rootView.findViewById(R.id.pressureTextView);
        pressureTextView.setText(cityObject.getCurrentObject().getPressure() +" hPa");

        humidityTextView = (TextView) rootView.findViewById(R.id.humidityTextView);
        humidityTextView.setText(cityObject.getCurrentObject().getHumidity() + " %");

        cloudiness = (TextView) rootView.findViewById(R.id.percentRainTextView);
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

        //This part is to set up citypage recylerview for daily weather
        dailyObjects = new ArrayList<>();
        dailyObjects = (ArrayList<DailyObject>) cityObject.getDailyObject();

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        citypageRecyclerAdapter = new CitypageRecyclerAdapter(this, dailyObjects);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(citypageRecyclerAdapter);

        backArrowImageView = (ImageView) rootView.findViewById(R.id.backArrowImageView);
        backArrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new SearchFragment()).commit();
            }
        });

        //Save favorite city Button
        saveButton = rootView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favCityName = cityObject.getCityName();
                favState    = cityObject.getStateName();
                favLat      = cityObject.getLat();
                favLon      = cityObject.getLon();

                ((MainActivity) getActivity()).getweather2(favLat, favLon, unit, favCityName, favState, "home_fav_city");

//((MainActivity) getActivity()).homeFragment.insertFavoriteCity();
//                ((MainActivity) getActivity()).homeFragment.displayFavoriteCity();

                saveButton.setVisibility(View.GONE);
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    public void setCityObject(CityObject cityObject) {
        this.cityObject = cityObject;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public String convertTimeStamp (String timeStamp, boolean isFullDate) {
        long timeStampLong = Long.parseLong(timeStamp) * 1000; //If you get the time in seconds, you have to multiply it by 1000
        Date d = new Date(timeStampLong);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        String dayString = String.valueOf(LocalDate.of(year, month, date).getDayOfWeek());
        String monthString = String.valueOf(LocalDate.of(year, month, date).getMonth());

        //Check for full date or only three letters day
        String time = null;
        if (isFullDate == true) {
            time = dayString + ", " + String.valueOf(date) + '-' + monthString + '-' + String.valueOf(year);
        } else {
            int shortDay = LocalDate.of(year, month, date).getDayOfWeek().getValue();
            switch (shortDay) {
                case 1:
                    time = "Mon";
                    break;
                case 2:
                    time = "Tue";
                    break;
                case 3:
                    time = "Wed";
                    break;
                case 4:
                    time = "Thur";
                    break;
                case 5:
                    time = "Fri";
                    break;
                case 6:
                    time = "Sat";
                    break;
                case 7:
                    time = "Sun";
                    break;
                default:
                    time = "Error";
                    break;
            }
        }

        return time;
    }
}