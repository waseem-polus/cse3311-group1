package com.example.cloud_cast;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class SettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    View rootView;

    private String lat;
    private String lon;
    private String unit;

    HomeFragment homeFragment = new HomeFragment();
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        Spinner dropdown = rootView.findViewById(R.id.unitsSpinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"","Metric", "Imperial"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);



        dropdown.setOnItemSelectedListener(this);
        return rootView;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //position i
            switch (i) {
                case 0: //Do nothing
                    break;
                case 1:
                    ((MainActivity) getActivity()).setUnit("metric");
                    ((MainActivity) getActivity()).getweather2(((MainActivity) getActivity()).getLatGPS(), ((MainActivity) getActivity()).getLonGPS(), ((MainActivity) getActivity()).getUnit(), ((MainActivity) getActivity()).getCityNameGPS(), "","home");
                    break;
                case 2:
                    ((MainActivity) getActivity()).setUnit("imperial");
                    ((MainActivity) getActivity()).getweather2(((MainActivity) getActivity()).getLatGPS(), ((MainActivity) getActivity()).getLonGPS(), ((MainActivity) getActivity()).getUnit(), ((MainActivity) getActivity()).getCityNameGPS(), "","home");
                    break;
            }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}