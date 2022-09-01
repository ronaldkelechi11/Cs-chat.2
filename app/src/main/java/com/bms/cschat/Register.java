package com.bms.cschat;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Register extends AppCompatActivity {
    EditText nameEditText,emailEditText,numberEditText,passwordEditText,cpasswordEditText;
    ImageView passwordToggle1,passwordToggle2;

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




        // To format the code the number to be Nigerian Number Format
        numberEditText.addTextChangedListener(new PhoneNumberFormattingTextWatcher("NG"));

        // First check the type that is there then changes it to it's opposite
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




    }// End of initial class
    public void goToMain(View view) {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    public void goToHomeScreen(View view) {
        Toast.makeText(getApplicationContext(), "You clicked Sign Up", Toast.LENGTH_SHORT).show();
    }
}