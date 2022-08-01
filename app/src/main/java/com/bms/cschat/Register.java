package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.*;

public class Register extends AppCompatActivity {
    ConstraintLayout toLogin;
    EditText nickname,email,password;
    Button registerButton;

    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nickname = findViewById(R.id.EditTextRegisterNickname);
        email = findViewById(R.id.EditTextRegisterEmail);
        password = findViewById(R.id.EditTextRegisterPassword);
        registerButton = findViewById(R.id.RegisterButton);
        t1 = findViewById(R.id.t1);
        String nicknametxt = nickname.getText().toString();
        String emailtxt = email.getText().toString();
        String passwordtxt = password.getText().toString();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Connection cn = DriverManager.getConnection("127.0.0.1","root","");
                    Statement stmt = cn.createStatement();
                    String insert = "INSERT INTO `users` (`id`, `nickname`, `email`, `password`) VALUES ('1', 'nicknametxt', 'emailtxt', 'passwordtxt');";
                    ResultSet rst = stmt.executeQuery(insert);
                } catch (SQLException throwables) {
                    t1.setText("Error");
                }
            }
        });



        toLogin = findViewById(R.id.toLogin);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
    }
}