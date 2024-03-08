package com.petcare.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Grooming extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming);
    }

    public void onCallIconClicked1(View view) {
        String phoneNumber1 = "0775195673"; // Add the phone number for call_icon1 here
        dialPhoneNumber(phoneNumber1);
    }

    public void onMsgIconClicked1(View view) {
        String phoneNumber1 = "0775195673"; // Add the phone number for msg_icon1 here
        openMessageApp(phoneNumber1);
    }

    public void onCallIconClicked2(View view) {
        String phoneNumber2 = "0775195673"; // Add the phone number for call_icon1 here
        dialPhoneNumber(phoneNumber2);
    }

    public void onMsgIconClicked3(View view) {
        String phoneNumber2 = "0775195673"; // Add the phone number for msg_icon1 here
        openMessageApp(phoneNumber2);
    }

    public void onCallIconClicked3(View view) {
        String phoneNumber2 = "0775195673"; // Add the phone number for call_icon1 here
        dialPhoneNumber(phoneNumber2);
    }

    public void onMsgIconClicked2(View view) {
        String phoneNumber2 = "0775195673"; // Add the phone number for msg_icon1 here
        openMessageApp(phoneNumber2);
    }


    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void openMessageApp(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:" + phoneNumber));
        startActivity(intent);
    }

    public void goToSurgeon (View view){

        Intent intent = new Intent(Grooming.this , Surgeon.class);
        startActivity(intent);
    }
}