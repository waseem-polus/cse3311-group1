package com.example.cloud_cast;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    String[] locations = {"Dallas", "Arlington","Dallas", "Arlington","Dallas"};
    int[] iconImages = {R.drawable.ic_rainy_fill0_wght400_grad0_opsz48, R.drawable.ic_air_fill0_wght400_grad0_opsz48};
    ListView listView;

    ArrayList<CityObject> favoriteCityList = new ArrayList<>();
    CityObject cityObject = new CityObject();


    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView) rootView.findViewById(R.id.customListView);
        favoriteCityList.add(cityObject);
        //CustomListViewBaseAdapter customBaseAdapter = new CustomListViewBaseAdapter(getContext(), locations, iconImages);
        CustomListViewBaseAdapter customBaseAdapter = new CustomListViewBaseAdapter(getContext(), favoriteCityList, iconImages);
        listView.setAdapter(customBaseAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }



}