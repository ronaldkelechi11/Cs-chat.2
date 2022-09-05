package com.bms.cschat;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;


public class Register extends AppCompatActivity {
    EditText nameEditText,emailEditText,numberEditText,passwordEditText,cpasswordEditText;
    ImageView passwordToggle1,passwordToggle2;
    ProgressBar progressBar;

    String PASSED_NAME = "name";
    String PASSED_EMAIL = "email";
    String PASSED_NUMBER = "number";
    String PASSED_PASSWORD = "passwprd";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //EditText Fields
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        numberEditText = findViewById(R.id.phoneEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        cpasswordEditText = findViewById(R.id.cpasswordEditText);

        //ImageView Fields
        passwordToggle1 = findViewById(R.id.password_toggle1);
        passwordToggle2 = findViewById(R.id.password_toggle2);

        //Progress Bar
        progressBar = findViewById(R.id.progressBarReg);


        // To format the code the number to be Nigerian Number Format
        numberEditText.addTextChangedListener(new PhoneNumberFormattingTextWatcher("NG"));

        // For the password toggle
        passwordToggle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordEditText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordToggle1.setImageResource(R.drawable.closed_eye);
                }
                else{
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordToggle1.setImageResource(R.drawable.open_eye);
                }
            }
        });

        // For the confirm Password Toggle
        passwordToggle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cpasswordEditText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    cpasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordToggle2.setImageResource(R.drawable.closed_eye);
                }
                else{
                    cpasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordToggle2.setImageResource(R.drawable.open_eye);
                }
            }
        });


    }// End of initial class

    public void goToMain(View view) {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
    public void toLogin(View view) {
        Intent tl = new Intent(getApplicationContext(),Login.class);
        startActivity(tl);
    }

    public void goToUploadProfilePic(View view) {
        String nameEditTextStr,emailEditTextStr,numberEditTextStr,passwordEditTextStr,cpasswordEditTextStr;

        // Initialization
        nameEditTextStr = nameEditText.getText().toString();
        emailEditTextStr = emailEditText.getText().toString();
        numberEditTextStr = numberEditText.getText().toString();
        passwordEditTextStr = passwordEditText.getText().toString();
        cpasswordEditTextStr = cpasswordEditText.getText().toString();

        // Form Validation
        if(nameEditTextStr.isEmpty()){
            nameEditText.setError("Name should not be empty");
            nameEditText.requestFocus();
            return;
        }
        if(emailEditTextStr.isEmpty()){
            emailEditText.setError("Email should not be empty");
            emailEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailEditTextStr).matches()){
            emailEditText.setError("Not a valid Email Address format");
            emailEditText.requestFocus();
            return;
        }
        if(numberEditTextStr.isEmpty()){
            numberEditText.setError("Phone Number should not be empty");
            numberEditText.requestFocus();
            return;
        }
        if(numberEditTextStr.length() < 9){
            numberEditText.setError("Number not a valid Nigerian Number");
        }
        if(passwordEditTextStr.length() < 6){
            passwordEditText.setError("Password length Should not be less than 6");
            passwordEditText.requestFocus();
            return;
        }
        if(!cpasswordEditTextStr.equals(passwordEditTextStr)){
            cpasswordEditText.setError("Passwords do not match");
            cpasswordEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
                Intent ti = new Intent(getApplicationContext(),UploadUserImage.class);
                    ti.putExtra(PASSED_NAME,nameEditTextStr);
                    ti.putExtra(PASSED_EMAIL,emailEditTextStr);
                    ti.putExtra(PASSED_NUMBER,numberEditTextStr);
                    ti.putExtra(PASSED_PASSWORD,passwordEditTextStr);
                startActivity(ti);
    }


}