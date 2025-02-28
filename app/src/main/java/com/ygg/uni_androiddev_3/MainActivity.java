package com.ygg.uni_androiddev_3;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // super secret toast that you get by clicking on Lario image
    // its also randomized from 3 quotes :)
    public void superstar(View v) {
        String[] quotes = {"Luisiario", "Everybody wanna be a superstar",
                "Make a lotta money drive a fancy car"};
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