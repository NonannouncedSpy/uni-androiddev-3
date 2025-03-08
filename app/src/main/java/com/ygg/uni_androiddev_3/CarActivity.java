package com.ygg.uni_androiddev_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class CarActivity extends AppCompatActivity {

    private String larioCar;

    private void refreshCar() {
        final EditText carField = findViewById(R.id.car_edit);
        carField.setText(larioCar);
    }

    private void drive() {
        final EditText carField = findViewById(R.id.car_edit);
        larioCar = carField.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Bundle data = getIntent().getExtras();
        larioCar = data.getString("car");
        refreshCar();

        Log.w("LARIO3_CAR", "Started by user " + data.getString("username"));
        Log.w("LARIO3_CAR", larioCar);

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent returnIntent = new Intent(getApplicationContext(), SecondActivity.class);

                returnIntent.putExtra("car", larioCar);

                setResult(CarActivity.RESULT_OK, returnIntent);
                finish();
            }
        });

        final Button driveButton = findViewById(R.id.car_button);
        driveButton.setOnClickListener(v -> drive());
    }
}