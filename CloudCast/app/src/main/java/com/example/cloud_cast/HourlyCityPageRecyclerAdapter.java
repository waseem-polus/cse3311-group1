package com.example.cloud_cast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HourlyCityPageRecyclerAdapter extends RecyclerView.Adapter<HourlyCityPageRecyclerAdapter.MyHolder> {
    private final CityPageFragment cityPageFragment;
    private ArrayList<HourlyObject> hourlyObjects;
    private String unit;
    private String iconCode;

    public HourlyCityPageRecyclerAdapter(CityPageFragment fragment, ArrayList<HourlyObject> hourlyObjects, String unit) {
        this.cityPageFragment = fragment;
        this.hourlyObjects = hourlyObjects;
        this.unit = unit;
    }



    @NonNull
    @Override
    public HourlyCityPageRecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourlycitypage_recyclerview_item,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyCityPageRecyclerAdapter.MyHolder holder, int position) {
        holder.timeTextView.setText(cityPageFragment.convertTimeStamp(hourlyObjects.get(position).getCurrentTime(), false, true));
        holder.tempTextView.setText(hourlyObjects.get(position).getTemperature() + " °");
        holder.cloudTextView.setText(hourlyObjects.get(position).getCloudiness() + " %");
        if (unit.equals("metric")) {
            holder.windTextView.setText(hourlyObjects.get(position).getWindSpeed() + " m/s");
        } else {
            holder.windTextView.setText(hourlyObjects.get(position).getWindSpeed() + " mph");
        }

        iconCode = hourlyObjects.get(position).getWeatherObjectList().get(0).getIcon();
        Picasso.get().load("https://openweathermap.org/img/wn/" + iconCode + ".png").into(holder.iconImageView);
    }

    @Override
    public int getItemCount() {
        return hourlyObjects == null ? 0 : hourlyObjects.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView timeTextView;
        ImageView iconImageView;
        TextView tempTextView;
        TextView cloudTextView;
        TextView windTextView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = (TextView) itemView.findViewById(R.id.timeTextView);
            iconImageView = (ImageView) itemView.findViewById(R.id.iconWeatherImageView);
            tempTextView = (TextView) itemView.findViewById(R.id.tempTextView);
            cloudTextView = (TextView) itemView.findViewById(R.id.cloudinessTextView);
            windTextView = (TextView) itemView.findViewById(R.id.windSpeedTextView);
        }
    }
}
