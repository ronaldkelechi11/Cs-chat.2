package com.bms.cschat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Timer;

public class Login extends AppCompatActivity{
    ConstraintLayout toRegister;
    EditText email,password;
    ProgressBar progressBar;
    Button loginButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Buttons
        toRegister = findViewById(R.id.toRegister);
        loginButton = findViewById(R.id.LoginButton);

        //EditText
        email = findViewById(R.id.EditTextLoginEmail);
        password = findViewById(R.id.EditTextLoginPassword);

        //Progress Bar
        progressBar = findViewById(R.id.progressBarLogin);

        //Firebase
        mAuth = FirebaseAuth.getInstance();


        // Button Functionality for To Register click
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(getApplicationContext(),Register.class);
                startActivity(t);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailSt = email.getText().toString();
                String passwordSt = password.getText().toString();

                //Front-End Validation
                if(emailSt.isEmpty()){
                    email.setError("Email cannot be empty");
                    email.requestFocus();
                    return;
                }
                if(passwordSt.isEmpty()){
                    password.setError("Password cannot be empty");
                    password.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(emailSt).matches()){
                    email.setError("Not a valid Email Address");
                    email.requestFocus();
                    return;
                }
                if(passwordSt.length() < 6){
                    password.setError("Invalid Password");
                    password.requestFocus();
                    return;
                }


            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(emailSt,passwordSt)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String emailExist = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                                if(emailExist.equals(emailSt)){
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(),HomeScreen.class);
                                    startActivity(i);
                                    finish();
                                }
                                else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(), "Email Does not Exist in Database", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "Bad Internet Connection", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

            }
        });

    }
}