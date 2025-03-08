package com.ygg.uni_androiddev_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // super secret toast that you get by clicking on Lario image
    public void superstar(View v) {
        // poor imitation of "weights"; normal quotes with w of 3, a rare one with w of 1
        String[] quotes = {"Luisiario", "Luisiario", "Luisiario",
                "Everybody wanna be a superstar", "Everybody wanna be a superstar", "Everybody wanna be a superstar",
                "Make a lotta money drive a fancy car", "Make a lotta money drive a fancy car", "Make a lotta money drive a fancy car",
                "\uD83D\uDC41"};
        Random rand = new Random();

        Toast.makeText(this, quotes[rand.nextInt(quotes.length)] , Toast.LENGTH_SHORT).show();
    }

    private void begin() {
        Intent secondIntent = new Intent(getApplicationContext(), SecondActivity.class);

        startActivity(secondIntent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* animating Lario */
        // first: create the animation with specific attributes
        RotateAnimation rotateAnim = new RotateAnimation(
                0f, -360f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        // and have it interpolated
        rotateAnim.setInterpolator(new LinearInterpolator());
        // and have it go forever
        rotateAnim.setRepeatCount(RotateAnimation.INFINITE);
        // and make it take 2 seconds to do a full loop
        rotateAnim.setDuration(2000);

        // assign the animation to our image
        final ImageView larioView = findViewById(R.id.main_img_lario);
        larioView.setAnimation(rotateAnim);

        /* button to switch to second activity */
        final Button beginButton = findViewById(R.id.main_button_start);

        beginButton.setOnClickListener(v -> begin());
    }

}