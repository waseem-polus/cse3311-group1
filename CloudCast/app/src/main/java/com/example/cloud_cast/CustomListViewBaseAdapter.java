package com.example.cloud_cast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListViewBaseAdapter extends BaseAdapter {

    Context context;
    //String[] locations;
    ArrayList<CityObject> cityObjectArrayList = new ArrayList<>();
    int[] iconImages;
    ListView listView;
    LayoutInflater inflater;

    public CustomListViewBaseAdapter() {
    }

//    public CustomListViewBaseAdapter(Context context,  String[] locations, int[] iconImages) {
//        this.context = context;
//        this.locations = locations;
//        this.iconImages = iconImages;
//        inflater = LayoutInflater.from(context); //this context received from mainActivity
//    }

    public CustomListViewBaseAdapter(Context context,  ArrayList<CityObject> cityObjectArrayList, int[] iconImages) {
        this.context = context;
        this.cityObjectArrayList = cityObjectArrayList;
        this.iconImages = iconImages;
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
        view = inflater.inflate(R.layout.custom_list_view, null);

        listView = (ListView) view.findViewById(R.id.customListView);
        TextView locationTextView = (TextView) view.findViewById(R.id.locationTextView);
        //locationTextView.setText(locations[position]);

            //locationTextView.setText(cityObjectArrayList.get(position).getCityName());

//            ImageView thermoIconImage = (ImageView) view.findViewById(R.id.thermoIconImageView);
//            thermoIconImage.setImageResource(R.drawable.ic_thermometer_fill0_wght400_grad0_opsz48);

            TextView tempTextView = (TextView) view.findViewById(R.id.tempTextView);
            tempTextView.setText("80");

//            ImageView rainIconImage = (ImageView) view.findViewById(R.id.rainIconImageView);
//            rainIconImage.setImageResource((R.drawable.ic_rainy_fill0_wght400_grad0_opsz48));

            TextView percentRainTextView = (TextView) view.findViewById(R.id.percentRainTextView);
            percentRainTextView.setText("50");


            return view;
        }

}
