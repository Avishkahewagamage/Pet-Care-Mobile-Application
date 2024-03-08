package com.petcare.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the ConstraintLayout by its ID
        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);

        // Set an OnClickListener for the ConstraintLayout
        mainLayout.setOnClickListener(v -> {
            // When the layout is clicked, start the Splash_1Activity
            Intent intent = new Intent(MainActivity.this, Surgeon.class);
            startActivity(intent);
        });
    }
}
