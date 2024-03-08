package com.petcare.petcare;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    EditText mpetName, mpetAge, mpetBreed;
    CheckBox mCheckBoxcat, mCheckBoxdog;
    Button mSubmit;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mpetName = findViewById(R.id.petName);
        mpetAge = findViewById(R.id.petAge);
        mpetBreed = findViewById(R.id.petBreed);
        mCheckBoxcat = findViewById(R.id.checkbox1);
        mCheckBoxdog = findViewById(R.id.checkbox2);
        mSubmit = findViewById(R.id.submit);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = mpetName.getText().toString().trim();
                String age = mpetAge.getText().toString().trim();
                String breed = mpetBreed.getText().toString().trim();
                String gender = "";

                if(TextUtils.isEmpty(name)){
                    mpetName.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(age)){
                    mpetAge.setError("First Name is Required");
                    return;
                }

                if(TextUtils.isEmpty(breed)){
                    mpetBreed.setError("Last Name is Required");
                    return;
                }

                if (mCheckBoxcat.isChecked()) {
                    gender = "Cat";
                } else if (mCheckBoxdog.isChecked()) {
                    gender = "Dog";
                } else {
                    Toast.makeText(EditProfile.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Register the user in Firebase
                String finalGender = gender;
                fAuth.createUserWithEmailAndPassword(name,breed).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(EditProfile.this, "User Created", Toast.LENGTH_SHORT).show();

                            String userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("pets").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("name", name);
                            user.put("age", age);
                            user.put("breed", breed);
                            user.put("gender", finalGender);
                            documentReference.set(user).addOnSuccessListener(unused -> {
                                Log.d(TAG, "onSuccess: User Profile created for " + userID);
                                startActivity(new Intent(getApplicationContext(), Login.class));
                            });
                        } else {
                            Toast.makeText(EditProfile.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
