package com.example.cloud_cast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        //final Button placeHolderButton = (Button) findViewById(R.id.ChrisPlaceHolderButton);
        //placeHolderButton.setOnClickListener(v -> {
            // Code here executes on main thread after user presses button
            //Intent intent = new Intent(v.getContext(), SearchWeather.class);
           // startActivity(intent);
       // });
        //omair mohammed123
    }


}