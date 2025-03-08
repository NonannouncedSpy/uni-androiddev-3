package com.ygg.uni_androiddev_3;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class SuperstarActivity extends AppCompatActivity {

    private ArrayList<String> larioPublishes = new ArrayList<>();

    private void refreshList() {
        final TextView hitList = findViewById(R.id.star_text_hitlist);

        StringBuilder list = new StringBuilder();
        for (int i = 0; i < larioPublishes.size(); i++) {
            list.append(i+1).append(". ").append(larioPublishes.get(i));
            if (i != larioPublishes.size()-1) { list.append("\n"); }
        }

        hitList.setText(list.toString());
    }

    private void publish() {
        final EditText hitline = findViewById(R.id.star_edittext);
        larioPublishes.add(hitline.getText().toString());
        hitline.getText().clear();
        refreshList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superstar);

        Bundle data = getIntent().getExtras();
        larioPublishes = data.getStringArrayList("publishes");
        refreshList();

        Log.w("LARIO3_STAR", "Started by user " + data.getString("username"));
        larioPublishes.forEach(i -> Log.w("LARIO3_STAR", i));

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Log.w("LARIO3_STAR", "We backin'");
                        Intent returnIntent = new Intent(getApplicationContext(), SecondActivity.class);

                        returnIntent.putStringArrayListExtra("publishes", larioPublishes);

                        setResult(SuperstarActivity.RESULT_OK, returnIntent);
                        finish();
                    }
                });

        final Button publishButton = findViewById(R.id.star_button);
        publishButton.setOnClickListener(v -> publish());

        final TextView hitList = findViewById(R.id.star_text_hitlist);
        hitList.setMovementMethod(new ScrollingMovementMethod());
    }
}