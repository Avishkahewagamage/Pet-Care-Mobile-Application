package com.petcare.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Splash2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_2);
    }

    public void goToSplash3 (View view){

        Intent intent = new Intent(Splash2.this , Splash3.class);
        startActivity(intent);
    }
}
