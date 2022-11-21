package com.example.cloud_cast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CitypageRecyclerAdapter extends RecyclerView.Adapter<CitypageRecyclerAdapter.MyHolder> {
    private final CityPageFragment cityPageFragment;
    private ArrayList<DailyObject> dailyObjects;
    String iconCode;

    public CitypageRecyclerAdapter(CityPageFragment fragment, ArrayList<DailyObject> dailyObjects) {
        this.cityPageFragment = fragment;
        this.dailyObjects = dailyObjects;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dailycitypage_recyclerview_item,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.dayTextView.setText(cityPageFragment.convertTimeStamp(dailyObjects.get(position).getDay(), false));
        holder.highTempDaily.setText(dailyObjects.get(position).getTemp().getMax() + " °");
        holder.lowTempDaily.setText(dailyObjects.get(position).getTemp().getMin() + " °");
        iconCode = dailyObjects.get(position).getWeatherObjectList().get(0).getIcon();
        Picasso.get().load("https://openweathermap.org/img/wn/" + iconCode + ".png").into(holder.iconDaily);
    }

    @Override
    public int getItemCount() {
        return dailyObjects == null ? 0 : dailyObjects.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView highTempDaily;
        TextView dayTextView;
        TextView lowTempDaily;
        ImageView iconDaily;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            highTempDaily = itemView.findViewById(R.id.highTempDaily);
            lowTempDaily  = itemView.findViewById(R.id.lowTempDaily);
            dayTextView   = itemView.findViewById(R.id.dayTextView);
            iconDaily     = itemView.findViewById(R.id.iconDaily);
        }
    }
}
