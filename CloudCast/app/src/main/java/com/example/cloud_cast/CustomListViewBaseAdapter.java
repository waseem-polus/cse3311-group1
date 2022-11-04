package com.example.cloud_cast;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.ListMenuItemView;

import java.util.ArrayList;

public class CustomListViewBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<CityObject> cityObjectArrayList;
    ListView listView;
    LayoutInflater inflater;

    public CustomListViewBaseAdapter() {
    }

    public CustomListViewBaseAdapter(Context context,  ArrayList<CityObject> cityObjectArrayList) {
        this.context = context;
        this.cityObjectArrayList = cityObjectArrayList;
        //this.iconImages = iconImages;
        inflater = LayoutInflater.from(context); //this context received from mainActivity
    }

    @Override
    public int getCount() {
        return cityObjectArrayList == null ? 0 : cityObjectArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //view = inflater.inflate(R.layout.custom_list_view, null);
       view = LayoutInflater.from(inflater.getContext()).inflate(R.layout.custom_list_view, viewGroup, false);

        listView = (ListView) view.findViewById(R.id.customListView);
        if (cityObjectArrayList == null){
            TextView locationTextView = (TextView) view.findViewById(R.id.locationTextView);
            //locationTextView.setText(locations[position]);

            //locationTextView.setText(cityObjectArrayList.get(position).getCityName());

            TextView tempTextView = (TextView) view.findViewById(R.id.tempTextView);
            tempTextView.setText("80");

            TextView percentRainTextView = (TextView) view.findViewById(R.id.percentRainTextView);
            percentRainTextView.setText("50");
        } else {
            TextView emptyList = view.findViewById(R.id.emptyListView);
            emptyList.setText("Empty");
        }




            return view;
        }

}
