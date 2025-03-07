package com.ygg.uni_androiddev_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class SuperstarActivity extends AppCompatActivity {

    private ArrayList<String> larioPublishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superstar);

        Bundle data = getIntent().getExtras();
        larioPublishes = (ArrayList<String>) data.get("publishes");

        Log.w("LARIO3_STAR", "Started by user " + data.getString("username"));
        larioPublishes.forEach(i -> Log.w("LARIO3_STAR", i));

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Intent returnIntent = new Intent(getApplicationContext(), SecondActivity.class);

                        returnIntent.putExtra("publishes", larioPublishes);

                        setResult(1, returnIntent);
                        finish();
                    }
                });
    }
}