package com.ygg.uni_androiddev_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class CarActivity extends AppCompatActivity {

    private String larioCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Bundle data = getIntent().getExtras();
        larioCar =data.getString("car");

        Log.w("LARIO3_CAR", "Started by user " + data.getString("username"));
        Log.w("LARIO3_CAR", larioCar);

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent returnIntent = new Intent(getApplicationContext(), SecondActivity.class);

                returnIntent.putExtra("car", larioCar);

                setResult(3, returnIntent);
                finish();
            }
        });
    }
}