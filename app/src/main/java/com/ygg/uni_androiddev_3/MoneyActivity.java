package com.ygg.uni_androiddev_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.OnBackPressedCallback;
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

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Intent returnIntent = new Intent(getApplicationContext(), SecondActivity.class);

                        returnIntent.putExtra("coins", larioCoins.intValue());

                        setResult(2, returnIntent);
                        finish();
                    }
                });
    }
}