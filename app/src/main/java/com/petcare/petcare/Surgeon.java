package com.petcare.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Surgeon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgeon);
    }

    public void goToVetrinary_surgeon (View view){

        Intent intent = new Intent(Surgeon.this , Veterinary_surgeon.class);
        startActivity(intent);
    }

    public void goToTrainer (View view){

        Intent intent = new Intent(Surgeon.this , Trainer.class);
        startActivity(intent);
    }

    public void goToGrooming (View view){

        Intent intent = new Intent(Surgeon.this , Grooming.class);
        startActivity(intent);
    }
}