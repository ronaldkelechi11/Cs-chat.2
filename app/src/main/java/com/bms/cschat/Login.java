package com.bms.cschat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Timer;

public class Login extends AppCompatActivity{
    EditText fullNameEditText,passwordEditText;
    ImageView passwordToogle;
    CardView loginButton;
    TextView forgottenPassword, dontHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fullNameEditText = findViewById(R.id.fullnameEditText);
        passwordEditText = findViewById(R.id.passwordLoginEditText);
        passwordToogle = findViewById(R.id.password_toggleLogin);
        loginButton = findViewById(R.id.loginButton);
        forgottenPassword = findViewById(R.id.textView8);
        dontHaveAccount = findViewById(R.id.toRegister);

        // TODO: ADD FORGOTTEN PASSWORD PAGE

        //Re-director to Register from Login
        dontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(getApplicationContext(),Register.class);
                startActivity(t);
                finish();
            }
        });

        //Password Toggle
        passwordToogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordEditText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordToogle.setImageResource(R.drawable.closed_eye);
                }
                else{
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordToogle.setImageResource(R.drawable.open_eye);
                }
            }
        });

        //Login Button will use click listener instead
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), fullNameEditText.getText().toString() + " " + passwordEditText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }//End of initial Class

    public void goToMain(View view) {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}