package com.example.cloud_cast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//This api is to take city name and return lan, lot
public interface weatherapi1 {
    @GET("direct")
    Call<List<SearchCityInfo>> getweather1(@Query("q") String cityname, @Query("limit") String limit, @Query("appid") String apikey);
}
