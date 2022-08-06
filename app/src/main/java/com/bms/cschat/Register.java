package com.bms.cschat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.bms.cschat.classes.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    ConstraintLayout toLogin;
    EditText nickname,email,password;
    Button registerButton;
    ProgressBar progressBar;
    FirebaseAuth  mAuth;

    SharedPreferences myPref;
    SharedPreferences.Editor myPrefeditor;
    private static final String SHARED_PREFERNCE_NAME = "mySharedPreferences";
    private  static final String KEY = "default";//Default is the default value



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        //Progress Bar
        progressBar  = findViewById(R.id.progressBarRegister);

        // Buttons
        toLogin = findViewById(R.id.toLogin);
        registerButton = findViewById(R.id.RegisterButton);

        //EditTexts
        nickname = findViewById(R.id.EditTextRegisterNickname);
        email = findViewById(R.id.EditTextRegisterEmail);
        password = findViewById(R.id.EditTextRegisterPassword);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        // Button Functionality for To login click
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(getApplicationContext(),Login.class);
                startActivity(t);
            }
        });



        // Button Functionality to Register User
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Progressbar visibility
                progressBar.setVisibility(View.VISIBLE);

                //EditText to String
                String nicknameST = nickname.getText().toString();
                String emailST = email.getText().toString();
                String passwordST = password.getText().toString();

                // Front-End Validation
                if(nicknameST.isEmpty()){
                    nickname.setError("Nickname cannot be Empty");
                    nickname.requestFocus();
                    return;
                }
                if(emailST.isEmpty()){
                    email.setError("Email cannot be Empty");
                    email.requestFocus();
                    return;
                }
                if(passwordST.isEmpty()){
                    password.setError("Password cannot be Empty");
                    password.requestFocus();
                    return;
                }
                if(passwordST.length() < 6){
                    password.setError("Password must be greater than 6");
                    password.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(emailST).matches()){
                    email.setError("Not a valid email address");
                    email.requestFocus();
                    return;
                }


        mAuth.createUserWithEmailAndPassword(emailST,passwordST).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful()){
                    User user = new User(emailST,nicknameST);

                    //Input to FireBase DB with name as user ID
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                //Saving of Shared Preference Value
                                myPref = getSharedPreferences(SHARED_PREFERNCE_NAME,MODE_PRIVATE);
                                myPrefeditor = myPref.edit();

                                // Value of shared Preference is true
                                myPrefeditor.putString(KEY,"true");
                                myPrefeditor.apply();


                                Intent i = new Intent(getApplicationContext(),HomeScreen.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Registration Unsuccessful",Toast.LENGTH_SHORT).show();
                                System.out.println("Authentication Issues");
                            }

                        }
                    });
                }
                else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Bad Internet Connection", Toast.LENGTH_SHORT).show();
                    System.out.println("Registration Issues");
                }



            }
        });

            }
        });





    }

}