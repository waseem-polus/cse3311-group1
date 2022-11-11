package com.example.cloud_cast;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ListView listView;

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






        //This is for listView in home screen
//        listView = (ListView) rootView.findViewById(R.id.customListView);
//
//        CustomListViewBaseAdapter customBaseAdapter = new CustomListViewBaseAdapter(getContext(), favoriteCityList);
//
//        favoriteCityList.add(cityObject);
//
//
//        listView.setAdapter(customBaseAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    public void setCityObject(CityObject cityObject) {
        this.cityObject = cityObject;
    }



}