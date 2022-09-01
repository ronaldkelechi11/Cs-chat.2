package com.bms.cschat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class UploadUserImage extends AppCompatActivity {
    ImageView uploadImage;
    CardView uploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_user_image);

        uploadImage = findViewById(R.id.uploadImage);
        uploadButton = findViewById(R.id.uploadButton);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Opening File Manager for New User Image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goToMain(View view) {
        // TODO: Remove the toast below
        Toast.makeText(getApplicationContext(), "Back to SignUp", Toast.LENGTH_SHORT).show();
    }
}