package com.ygg.uni_androiddev_3;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    }
}