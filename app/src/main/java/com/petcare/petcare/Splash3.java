package com.petcare.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.petcare.petcare.Login;
import com.petcare.petcare.R;

public class Splash3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_3);

        // Find the TextView by its ID
        TextView textView = findViewById(R.id.textView4);

        // Set an OnClickListener for the TextView
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the TextView is clicked, navigate to the login page
                Intent intent = new Intent(Splash3.this, Login.class); // Replace LoginActivity with the actual name of your login activity
                startActivity(intent);
            }
        });
    }
    public void goToSignUp (View view){

        Intent intent = new Intent(Splash3.this , SignUp.class);
        startActivity(intent);
    }
}
