package com.ygg.uni_androiddev_3;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MoneyActivity extends AppCompatActivity {

    private Integer larioCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        Bundle data = getIntent().getExtras();
        larioCoins = data.getInt("coins", 0);

        Log.w("LARIO3_COIN", "Started by user " + data.getString("username"));
        Log.w("LARIO3_COIN", larioCoins.toString());
    }
}