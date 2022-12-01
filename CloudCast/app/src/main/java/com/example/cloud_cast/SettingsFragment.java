package com.example.cloud_cast;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;


public class SettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    View rootView;

    private String lat;
    private String lon;
    private String unit;

    private ImageView deleteButton;
    private ImageView locationSettingsButton;

    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);


        //Location Settings Button
//        locationSettingsButton = rootView.findViewById(R.id.locationSettingsImageView);
//        locationSettingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(callGPSSettingIntent);
//            }
//        });

        Spinner dropdown = rootView.findViewById(R.id.unitsSpinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"","Metric", "Imperial"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        //Delete button
        deleteButton = (ImageView) rootView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(view);
            }
        });

        editor = ((MainActivity) getActivity()).preferences.edit();

        return rootView;

    }

    private void showAlertDialog(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Warning!");
        alert.setMessage("Delete all information permanently?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new DatabaseHelper(getContext()).deleteAll();
                editor.putString("unit", "metric").commit();
                ((MainActivity) getActivity()).setUnit("metric");
                ((MainActivity) getActivity()).getweather2(((MainActivity) getActivity()).getLatGPS(), ((MainActivity) getActivity()).getLonGPS(), "metric", ((MainActivity) getActivity()).getCityNameGPS(), "","settings");

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });
        alert.create().show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { //position i
        // Creating an Editor object to edit(write to the file)
            switch (i) {
                case 0: //Do nothing
                    break;
                case 1:
                    // Storing the key and its value as the data fetched from edittext
                    editor.putString("unit", "metric").commit();
                    ((MainActivity) getActivity()).setUnit("metric");
                    ((MainActivity) getActivity()).getweather2(((MainActivity) getActivity()).getLatGPS(), ((MainActivity) getActivity()).getLonGPS(), ((MainActivity) getActivity()).getUnit(), ((MainActivity) getActivity()).getCityNameGPS(), "","home");
                    break;
                case 2:
                    // Storing the key and its value as the data fetched from edittext
                    editor.putString("unit", "imperial").commit();
                    ((MainActivity) getActivity()).setUnit("imperial");
                    ((MainActivity) getActivity()).getweather2(((MainActivity) getActivity()).getLatGPS(), ((MainActivity) getActivity()).getLonGPS(), ((MainActivity) getActivity()).getUnit(), ((MainActivity) getActivity()).getCityNameGPS(), "","home");
                    break;
            }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}