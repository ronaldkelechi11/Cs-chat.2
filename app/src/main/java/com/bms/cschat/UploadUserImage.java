package com.bms.cschat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class UploadUserImage extends AppCompatActivity {
    private ImageView uploadImage;
    private CardView uploadButton;


    private static final int PICK_IMAGE = 123;
    private Uri imagePath;

    AlertDialog alertDialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_user_image);

        uploadImage = findViewById(R.id.uploadImage);
        uploadButton = findViewById(R.id.uploadButton);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(pickImage,PICK_IMAGE);
            }
        });


    }//End of Initial Class

    //Surrounded activity result with try-catch statement to be on the safe side
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if(requestCode == PICK_IMAGE && resultCode == RESULT_OK)
            {
                assert data != null;
                imagePath = data.getData();
                uploadImage.setImageURI(imagePath);
                Toast.makeText(getApplicationContext(), "Image Added Successfully", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Image add error", Toast.LENGTH_SHORT).show();
        }
        //TODO: Add circular Image dependency
        super.onActivityResult(requestCode, resultCode, data);
    }



    public void goToMain(View view) {
        builder = new AlertDialog.Builder(UploadUserImage.this);
        builder.setCancelable(true);
        builder.setMessage("Are you sure you want to exit your information is not yet saved ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                alertDialog.cancel();
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.cancel();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }


}