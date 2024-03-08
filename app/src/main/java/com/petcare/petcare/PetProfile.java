package com.petcare.petcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PetProfile extends AppCompatActivity {

    private static final String TAG = "DisplayPetDetails";
// create a veriable
    private TextView mNameTextView;
    private TextView mAgeTextView;
    private TextView mBreedTextView;
    private TextView mTypeTextView;

    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize TextViews
        mNameTextView = findViewById(R.id.pet_name);
        mAgeTextView = findViewById(R.id.pet_age);
        mBreedTextView = findViewById(R.id.pet_breed);
        mTypeTextView = findViewById(R.id.pet_type);

        // Retrieve pet details from Firestore
        retrievePetDetails();
    }

    private void retrievePetDetails() {
        // collection called "pets" in Firestore
        // change collection and document IDs same as firestore

        db.collection("pets").document("your_document_id")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Retrieve data and set it to TextViews
                            mNameTextView.setText(document.getString("name"));
                            mAgeTextView.setText(String.valueOf(document.getLong("age")));
                            mBreedTextView.setText(document.getString("breed"));
                            mTypeTextView.setText(document.getString("type"));
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                });
    }
    public void gotoEditPrifile (View view){

        Intent intent = new Intent(PetProfile.this , EditProfile.class);
        startActivity(intent);
    }

}
