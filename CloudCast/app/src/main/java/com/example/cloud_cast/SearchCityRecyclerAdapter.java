package com.example.cloud_cast;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchCityRecyclerAdapter extends RecyclerView.Adapter<SearchCityRecyclerAdapter.MyViewHolder>{
    Context context;
    ArrayList<SearchCityInfo> list;

    public static int checkedPosition = -1; //The 0 is first item, -1 is hidden

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SearchCityInfo currentCity = list.get(position);
        checkedPosition = -1;
        holder.bind(currentCity);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cityName, countryName, stateName;
        ImageView selectImageView;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            cityName    = itemView.findViewById(R.id.cityView);
            stateName   = itemView.findViewById(R.id.stateView);
            countryName = itemView.findViewById(R.id.countryView);
            selectImageView = itemView.findViewById(R.id.selectImageView);
        }

        void bind(final SearchCityInfo searchCityInfo) {
            if (checkedPosition == -1) {
                selectImageView.setVisibility(View.GONE);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    selectImageView.setVisibility(View.VISIBLE);

                } else {
                    selectImageView.setVisibility(View.GONE);
                }
            }

            cityName.setText(searchCityInfo.getName());
            stateName.setText(searchCityInfo.getState());
            countryName.setText(searchCityInfo.getCountry());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectImageView.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }

                }
            });
        }
    }


}
