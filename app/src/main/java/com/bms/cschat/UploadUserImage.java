package com.bms.cschat;

import androidx.annotation.NonNull;
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

import com.bms.cschat.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadUserImage extends AppCompatActivity {
    private ImageView uploadImage;
    private CardView uploadButton;

    String PASSED_NAME = "name";
    String PASSED_EMAIL = "email";
    String PASSED_NUMBER = "number";
    String PASSED_PASSWORD = "passwprd";


    private static final int PICK_IMAGE = 123;
    private Uri imagePath;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


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
                if(imagePath != null){
                    createNewUser();
                }
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Image add error", Toast.LENGTH_SHORT).show();
        }
        //TODO: Add circular Image dependency
        super.onActivityResult(requestCode, resultCode, data);
    }



    private void createNewUser() {
        Intent intent = getIntent();
        String name = intent.getStringExtra(PASSED_NAME);
        String email = intent.getStringExtra(PASSED_EMAIL);
        String phone = intent.getStringExtra(PASSED_NUMBER);
        String password = intent.getStringExtra(PASSED_PASSWORD);

        User user = new User(name,email,phone);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users").child(mAuth.getCurrentUser().getUid());


        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Just a reduced th code to write in the main part
                            databaseReference.setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Intent tHome = new Intent(getApplicationContext(),HomeScreen.class);
                                                startActivity(tHome);
                                                Toast.makeText(getApplicationContext(), "SignUp Successful", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Login Un-Successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //Just to text if it, passed not to sure
        Toast.makeText(getApplicationContext(), name + " " + email, Toast.LENGTH_SHORT).show();
    }


    public void goToMain(View view) {
        builder = new AlertDialog.Builder(UploadUserImage.this);
        builder.setCancelable(true);
        builder.setTitle("Error");
        builder.setMessage("Are you sure you want to exit? Your profile creation has not been completed");
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