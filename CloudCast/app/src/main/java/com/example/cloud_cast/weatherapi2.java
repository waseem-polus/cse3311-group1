package com.example.cloud_cast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//This api is to take lat, lon and returns all info about weather
public interface weatherapi2 {
    @GET("onecall")
    Call<CityObject> getweather2(@Query("lat")String lat, @Query("lon")String lon,@Query("exclude")String exclude, @Query("units")String unit,@Query("appid")String apikey);
}