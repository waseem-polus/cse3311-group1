package com.example.cloud_cast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchCityRecyclerAdapter extends RecyclerView.Adapter<SearchCityRecyclerAdapter.MyViewHolder>{
    Context context;
    ArrayList<SearchCityInfo> list;

    public SearchCityRecyclerAdapter(Context context, ArrayList<SearchCityInfo> list){
        this.context = context;
        this.list=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_weather_list_element,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCityRecyclerAdapter.MyViewHolder holder, int position) {
        SearchCityInfo currentCityWeather = list.get(position);


        holder.cityName.setText(currentCityWeather.getCity());
        holder.countryName.setText(currentCityWeather.getCountry());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cityName, countryName;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            cityName    = itemView.findViewById(R.id.cityView);
            countryName = itemView.findViewById(R.id.countryView);
        }
    }
}
