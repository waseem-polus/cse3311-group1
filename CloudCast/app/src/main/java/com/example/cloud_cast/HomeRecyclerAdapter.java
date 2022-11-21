package com.example.cloud_cast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.MyHolder> {
    ArrayList<CityObject> favoriteCityList;

    public HomeRecyclerAdapter(ArrayList<CityObject> favoriteCityList) {
        this.favoriteCityList = favoriteCityList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_item,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        try {
            holder.cityName.setText(favoriteCityList.get(position).getCityName());
            holder.description.setText(favoriteCityList.get(position).getCurrentObject().getWeatherObjectList().get(0).getDescription());
            holder.temp.setText(favoriteCityList.get(position).getCurrentObject().getTemperature() + "°");
            holder.cloudiness.setText(favoriteCityList.get(position).getCurrentObject().getCloudiness() + "%");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return favoriteCityList == null ? 0 : favoriteCityList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView cityName;
        TextView description;
        TextView temp;
        TextView cloudiness;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityNameTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            temp = itemView.findViewById(R.id.tempTextView);
            cloudiness = itemView.findViewById(R.id.cloudinessTextView);
        }
    }
}
