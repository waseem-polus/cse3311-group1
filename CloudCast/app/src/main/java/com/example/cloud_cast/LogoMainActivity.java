package com.example.cloud_cast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.LocationServices;

import java.util.Locale;

public class LogoMainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    Intent welcomeIntent;
    Intent homeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_logo_main);

        sharedpreferences = getSharedPreferences(getString(R.string.app_name), getApplicationContext().MODE_PRIVATE);
        boolean showWelcome = sharedpreferences.getBoolean("isShowWelcomeScreen", false);

//        if (!showWelcome) {
        if (true) {
            //for first-time user to see the tutorial
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean("isShowWelcomeScreen", true);
                    editor.apply();

                    welcomeIntent = new Intent(getApplicationContext(), WelcomeMainActivity.class);
                    startActivity(welcomeIntent);
                    // on the below line we are finishing our current activity.
                    finish();
                }
            }, 1500);
        } else {
            //skipping tutorial
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                    //startActivity(homeIntent);
                    //finish();
                }
            }, 1500);
        }
    }


}