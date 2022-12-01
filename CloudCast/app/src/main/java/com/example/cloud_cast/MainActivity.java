package com.example.cloud_cast;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String apikey = "0ec192d57d6c5e00396f88cd7cad1f6e";
    public static final String exclude = "minutely, alerts";

    SharedPreferences preferences;

    private Retrofit retrofit2 = null;

    FusedLocationProviderClient mFusedLocationClient;
    Geocoder geocoder;
    private String latGPS = null;
    private String lonGPS = null;

    private String unit; //metric as default
    private String cityNameGPS = null;
    private int PERMISSION_ID = 44;

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    CityPageFragment cityPageFragment;// = new CityPageFragment();

    private CityObject cityObject = new CityObject();
    private CityObject favCityObject = new CityObject();
    private BackTask backTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        //get unit
        preferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        unit = preferences.getString("unit", "metric");

        cityPageFragment = new CityPageFragment();

        // start AsyncTask
        backTask = new BackTask();
        backTask.execute();

        //Inflate the layout(FrontEnd)
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //This is to setup bottomNavigation
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeNavi:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
                        return true;
                    case R.id.searchNavi:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new SearchFragment()).commit(); //SeachFragment needs to refresh every search
                        return true;
                    case R.id.settingsNavi:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new SettingsFragment()).commit();
                        return true;
                }
                return false;
            }
        });


    }


    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //This is for lat & lon by GPS
    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {
            // check if location is enabled
            if (isLocationEnabled()) {
                // getting last location from FusedLocationClient object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            latGPS = String.valueOf(location.getLatitude());
                            lonGPS = String.valueOf(location.getLongitude());
                            cityNameGPS = getCityNameGPS(latGPS, lonGPS);
                            getweather2(latGPS,lonGPS, unit,cityNameGPS, "","home");
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                finish();
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                //Default city when deny permission
                latGPS = "37.419857";
                lonGPS = "-122.078827";
                cityNameGPS = "Mountain View";
                getweather2(latGPS, lonGPS, unit, cityNameGPS, "","home");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }

    //    This is for second api to retrieve all information
    //     calledFrom: "home", "search"
    public void getweather2(String lat, String lon, String unit, String cityName, String stateName, String calledFrom){
        retrofit2 = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/3.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherapi2 myapi=retrofit2.create(weatherapi2.class);
        Call<CityObject> cityObjectCall=myapi.getweather2(lat, lon, exclude, unit, apikey);
        cityObjectCall.enqueue(new Callback<CityObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<CityObject>call, Response<CityObject>response) {
                if (response.code() == 404) {
                    Toast.makeText(getApplicationContext(), "Invalid latitude and longitude", Toast.LENGTH_LONG).show();
                } else if (!(response.isSuccessful())) {
                    Log.i("response.notSuccessful", "here");
                    Toast.makeText(getApplicationContext(), response.code()+" ", Toast.LENGTH_LONG).show();
                    return;
                } else {

                    if (calledFrom.equals("home")) {
                        cityObject = response.body();
                        assert cityObject != null;
                        cityObject.setCityName(cityName);
                        homeFragment.setCityObject(cityObject);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commitAllowingStateLoss();
                    }
                    else if (calledFrom.equals("search")) {
                        cityObject = response.body();
                        assert cityObject != null;
                        cityObject.setCityName(cityName);
                        cityObject.setStateName(stateName);
                        cityPageFragment.setCityObject(cityObject);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, cityPageFragment).commit();
                    }
                    else if (calledFrom.equals("home_fav_city")) {
                        favCityObject = response.body();
                        assert favCityObject != null;
                        favCityObject.setCityName(cityName);
                        favCityObject.setStateName(stateName);
                        homeFragment.setFavCityObject(favCityObject);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
                    } else if (calledFrom.equals("settings")) {
                        cityObject = response.body();
                        assert cityObject != null;
                        cityObject.setCityName(cityName);
                        homeFragment.setCityObject(cityObject);

                        homeFragment.setReadFromDataBase(true);
                        homeFragment.setFavCityObject(null);
                        homeFragment.setFavoriteCityList(new ArrayList<>());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<CityObject> call, Throwable t) {
                Log.i("Exception", String.valueOf(t.getMessage()));
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }

        });

    }

    private class BackTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            //Get Location(BackEnd)
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplication());
            geocoder = new Geocoder(getApplication(), Locale.getDefault());
            getLastLocation();
            return null;
        }

    }

    public String getCityNameGPS(String lat, String lon){
        //Use Geocode to get cityName
        List<Address> addressList = null; //Here 1 represent max location result to returned, by documents it recommended 1 to 5
        try {
            addressList = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lon), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cityNameGPS = addressList.get(0).getLocality();
        return cityNameGPS;
    }


    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public String getLatGPS() {
        return latGPS;
    }

    public String getLonGPS() {
        return lonGPS;
    }

    public String getCityNameGPS() {
        return cityNameGPS;
    }

    public CityPageFragment getCityPageFragment() {
        return cityPageFragment;
    }
}