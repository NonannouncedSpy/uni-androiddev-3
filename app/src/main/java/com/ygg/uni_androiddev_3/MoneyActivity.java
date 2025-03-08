package com.ygg.uni_androiddev_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MoneyActivity extends AppCompatActivity {

    private Integer larioCoins;

    // refresh "Current coins" textview
    private void refreshCoins() {
        final TextView coinText = findViewById(R.id.cash_2_text);
        coinText.setText(getString(R.string.cash_text_cash, larioCoins));
    }

    // add 1-7 coins to total
    private void clickCashButton() {
        Random rng = new Random();
        int amount =  rng.nextInt(7);
        larioCoins += amount;
        refreshCoins();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        Bundle data = getIntent().getExtras();
        larioCoins = data.getInt("coins", 0);
        refreshCoins();

        Log.w("LARIO3_COIN", "Started by user " + data.getString("username"));
        Log.w("LARIO3_COIN", larioCoins.toString());

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Intent returnIntent = new Intent(getApplicationContext(), SecondActivity.class);

                        returnIntent.putExtra("coins", larioCoins.intValue());

                        setResult(MoneyActivity.RESULT_OK, returnIntent);
                        finish();
                    }
                });

        final Button cashButton = findViewById(R.id.cash_button);
        cashButton.setOnClickListener(v -> clickCashButton());
    }
}