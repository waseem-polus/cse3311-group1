package com.example.cloud_cast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WelcomeMainActivity extends AppCompatActivity {
    private Intent homeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.welcome_main_activity);

        homeIntent = new Intent(getApplicationContext(), MainActivity.class);

    }

    public void yesIAmClick(View view) {
        setContentView(R.layout.home_icons_tutorial);
    }

    public void backHomeClick(View view) {
        setContentView(R.layout.welcome_main_activity);
    }

    public void nextHomeClick(View view) {
        setContentView(R.layout.weather_icons_tutorial);
    }

    public void backWeatherClick(View view) {
        setContentView(R.layout.home_icons_tutorial);
    }

    public void nextWeatherClick(View view) {
        setContentView(R.layout.add_favorite_cities);
    }

    public void backAddCitiesClick(View view) {
        setContentView(R.layout.weather_icons_tutorial);
    }

    public void nextAddCitiesClick(View view) {
        startActivity(homeIntent);
        finish();
    }

}