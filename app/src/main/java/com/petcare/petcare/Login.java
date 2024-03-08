package com.petcare.petcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    //ProgressBar progressBar;
    FirebaseAuth fAuth;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mCreateBtn = findViewById(R.id.createText);
        mLoginBtn = findViewById(R.id.loginBtn);

        fAuth = FirebaseAuth.getInstance();


          // This code pat is same as to the sign up( Authentication part)
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

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

                //progressBar.setVisibility(View.VISIBLE);


                // Authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      // When the login is successful, move to Navbar class.
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "User Login Success", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), NavBar.class));
                        }
                        else {
                            Toast.makeText(Login.this, "Error !" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

    }
}