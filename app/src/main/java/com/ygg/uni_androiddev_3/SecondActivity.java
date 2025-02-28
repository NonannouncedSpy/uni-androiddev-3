package com.ygg.uni_androiddev_3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    // 3 dummy scores; why 3? Because ! :)
    private Integer larioCoins;
    private String larioCar;
    private ArrayList<String> larioPublishes = new ArrayList<>();

    public SecondActivity() {
        resetScores();
    }

    private void resetScores() {
        larioCoins = 0;
        larioCar = "None";
        larioPublishes.clear();
    }

    // a little helper to make my goddamn towers less of a tower
    private void changeViewsVisibility(View[] views, int visibility) {
        for (View v : views) {
            v.setVisibility(visibility);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /* keep scores updated */
        final TextView scoreStar = findViewById(R.id.second_score_star);
        final TextView scoreCash = findViewById(R.id.second_score_cash);
        final TextView scoreCar = findViewById(R.id.second_score_car);

        scoreStar.setText(getString(R.string.second_score_star, larioPublishes.size()));
        scoreCash.setText(getString(R.string.second_score_cash, larioCoins));
        scoreCar.setText(getString(R.string.second_score_car, larioCar));

        final EditText whofield = findViewById(R.id.second_edit_who);

        final Button starButton = findViewById(R.id.second_button_superstar);
        final Button cashButton = findViewById(R.id.second_button_money);
        final Button carButton = findViewById(R.id.second_button_car);

        // do some funny stuff based on text editing field
        whofield.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            // if input is empty - hide everything
            // if not - show everything
            // and possibly catch impersonators
            @Override
            public void afterTextChanged(Editable s) {
                resetScores();

                if (s.toString().equalsIgnoreCase("lario")) {
                    findViewById(R.id.second_text_imposter).setVisibility(View.VISIBLE);
                    changeViewsVisibility(new View[] {starButton, cashButton, carButton,
                                    scoreStar, scoreCash, scoreCar},
                            View.GONE);
                    return;
                }
                else {
                    findViewById(R.id.second_text_imposter).setVisibility(View.GONE);
                }

                if (s.toString().isEmpty()) {
                    changeViewsVisibility(new View[] {starButton, cashButton, carButton,
                            scoreStar, scoreCash, scoreCar},
                            View.GONE);
                    return;
                }


                changeViewsVisibility(new View[] {starButton, cashButton, carButton,
                                scoreStar, scoreCash, scoreCar},
                        View.VISIBLE);
            }
        });
    }
}