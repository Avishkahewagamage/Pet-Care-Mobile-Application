package com.petcare.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Splash1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_1);
    }

    public void goToSplash2 (View view){

        Intent intent = new Intent(Splash1.this , Splash2.class);
        startActivity(intent);
    }
}
