package com.ygg.uni_androiddev_3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

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

            // after input is finished - show buttons if there is any text, otherwise hide
            // also sus out anybody who thinks they can be Lario.
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equalsIgnoreCase("lario")) {
                    findViewById(R.id.second_text_imposter).setVisibility(View.VISIBLE);
                    return;
                }
                else {
                    findViewById(R.id.second_text_imposter).setVisibility(View.GONE);
                }

                if (s.toString().isEmpty()) {
                    starButton.setVisibility(View.GONE);
                    cashButton.setVisibility(View.GONE);
                    carButton.setVisibility(View.GONE);
                    return;
                }

                starButton.setVisibility(View.VISIBLE);
                cashButton.setVisibility(View.VISIBLE);
                carButton.setVisibility(View.VISIBLE);
            }
        });
    }
}