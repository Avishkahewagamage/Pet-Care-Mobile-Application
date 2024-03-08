package com.petcare.petcare;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUp extends AppCompatActivity {

    //
    EditText  mFirstName, mEmail, mPassword, mLastName;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    //ProgressBar progressBar;

    FirebaseFirestore fStore;
    String userID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // assign veriable to xmal component
        mFirstName = findViewById(R.id.FirstName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLastName = findViewById(R.id.LastName);
        mRegisterBtn = findViewById(R.id.registerbtn);
        mLoginBtn = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance(); // use for get
        fStore = FirebaseFirestore.getInstance();
        //progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();

                // validate

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }

                if(password.length()<6){
                    mPassword.setError("Password must be more than six Charactos");
                }



                // Register the user in Firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_SHORT).show();

                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID); // store user id in a users key
                            Map<String, Object> user = new HashMap<>(); // Use HashMap to inset data to database
                            user.put("fullname",firstName);
                            user.put("email",email); //assign veriable to key
                            user.put("lastname",lastName);
                            documentReference.set(user).addOnSuccessListener(unused -> Log.d(TAG,"onSuccess: User Profile created for"+ userID));
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }
                        else {
                            Toast.makeText(SignUp.this, "Error !" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }

}