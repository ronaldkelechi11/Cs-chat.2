package com.bms.cschat;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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

        // First check the type that is there then changes it to it's opposite
        passwordToggle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean isPasswordType = passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD;
              if(isPasswordType){
                  passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                  passwordToggle1.setImageResource(R.drawable.closed_eye);
              }
              if(!isPasswordType){
                  passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                  passwordToggle1.setImageResource(R.drawable.open_eye);
              }
            }
        });


    }// End of initial class
    public void goToMain(View view) {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}