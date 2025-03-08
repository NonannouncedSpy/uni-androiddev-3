package com.ygg.uni_androiddev_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private String username;
    // 3 dummy scores; why 3? Because ! :)
    private Integer larioCoins;
    private String larioCar;
    private ArrayList<String> larioPublishes = new ArrayList<>();

    private void resetScores() {
        larioCoins = 0;
        larioCar = "None";
        larioPublishes.clear();
        refreshScores();
    }

    // a little helper to make my goddamn towers less of a tower
    private void changeViewsVisibility(View[] views, int visibility) {
        for (View v : views) {
            v.setVisibility(visibility);
        }
    }

    private ActivityResultLauncher<Intent> superstarCallback = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult res) {
                    if (res.getResultCode() != Activity.RESULT_OK) { return; }

                    Bundle data = res.getData().getExtras();
                    larioPublishes = data.getStringArrayList("publishes");
                    Log.w("LARIO3", "got publishes");

                    refreshScores();
                    }
                }
    );

    private ActivityResultLauncher<Intent> moneyCallback = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult res) {
                    if (res.getResultCode() != Activity.RESULT_OK) { return; }

                    Bundle data = res.getData().getExtras();
                    larioCoins = data.getInt("coins", 0);
                    Log.w("LARIO3", "got coins: " + larioCoins);

                    refreshScores();
                    }
                }
    );

    private ActivityResultLauncher<Intent> carCallback = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult res) {
                    if (res.getResultCode() != Activity.RESULT_OK) { return; }

                    Bundle data = res.getData().getExtras();
                    larioCar = data.getString("car");
                    Log.w("LARIO3", "got car: " + larioCar);

                    refreshScores();
                    }
                }
    );

    private void switchToHits(View v) {
        Intent hitIntent = new Intent(getApplicationContext(), SuperstarActivity.class);

        hitIntent.putExtra("username", username);
        hitIntent.putStringArrayListExtra("publishes", larioPublishes);

        superstarCallback.launch(hitIntent);
    }

    private void switchToCash(View v) {
        Intent cashIntent = new Intent(getApplicationContext(), MoneyActivity.class);

        cashIntent.putExtra("username", username);
        cashIntent.putExtra("coins", larioCoins);

        moneyCallback.launch(cashIntent);
    }

    private void switchToCar(View v) {
        Intent carIntent = new Intent(getApplicationContext(), CarActivity.class);

        carIntent.putExtra("username", username);
        carIntent.putExtra("car", larioCar);

        carCallback.launch(carIntent);
    }

    private void refreshScores() {
        final TextView scoreStar = findViewById(R.id.second_score_star);
        final TextView scoreCash = findViewById(R.id.second_score_cash);
        final TextView scoreCar = findViewById(R.id.second_score_car);

        scoreStar.setText(getString(R.string.second_score_star, larioPublishes.size()));
        scoreCash.setText(getString(R.string.second_score_cash, larioCoins));
        scoreCar.setText(getString(R.string.second_score_car, larioCar));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        resetScores();

        final TextView scoreStar = findViewById(R.id.second_score_star);
        final TextView scoreCash = findViewById(R.id.second_score_cash);
        final TextView scoreCar = findViewById(R.id.second_score_car);

        final EditText whofield = findViewById(R.id.second_edit_who);

        final Button starButton = findViewById(R.id.second_button_superstar);
        final Button cashButton = findViewById(R.id.second_button_money);
        final Button carButton = findViewById(R.id.second_button_car);

        // button click listeners
        starButton.setOnClickListener(v -> switchToHits(v));
        cashButton.setOnClickListener(v -> switchToCash(v));
        carButton.setOnClickListener(v -> switchToCar(v));

        // do some funny stuff based on text editing field
        whofield.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                resetScores();
                View[] viewsWeToggle = {starButton, cashButton, carButton, scoreStar, scoreCash, scoreCar};

                // possibly catch impersonators and yell at them
                if (s.toString().equalsIgnoreCase("lario")) {
                    findViewById(R.id.second_text_imposter).setVisibility(View.VISIBLE);
                    changeViewsVisibility(viewsWeToggle, View.GONE);
                    return;
                }
                else {
                    findViewById(R.id.second_text_imposter).setVisibility(View.GONE);
                }

                // if input is empty - hide everything
                if (s.toString().isEmpty()) {
                    changeViewsVisibility(viewsWeToggle, View.GONE);
                    return;
                }

                // if not - show everything, and record the name
                changeViewsVisibility(viewsWeToggle, View.VISIBLE);
                username = s.toString();
            }
        });
    }
}