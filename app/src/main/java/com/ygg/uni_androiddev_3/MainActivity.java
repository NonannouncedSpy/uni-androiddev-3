package com.ygg.uni_androiddev_3;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // super secret toast that you get by clicking on Lario image
    public void superstar(View v) {
        Toast.makeText(this, R.string.main_superstar, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // animating Lario
        // first: create the animation with specific attributes
        RotateAnimation rotateAnim = new RotateAnimation(
                0f, 360f,
                0f, 0f);
        // and have it interpolated
        rotateAnim.setInterpolator(new LinearInterpolator());
        // and have it go forever
        rotateAnim.setRepeatCount(RotateAnimation.INFINITE);
        // and make it take 2 seconds to do a full loop
        rotateAnim.setDuration(2000);

        // assign the animation to our image
        final ImageView larioView = findViewById(R.id.main_img_lario);
        larioView.setAnimation(rotateAnim);
    }

}