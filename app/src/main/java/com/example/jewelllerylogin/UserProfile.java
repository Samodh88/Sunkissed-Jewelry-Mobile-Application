package com.example.jewelllerylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UserProfile extends AppCompatActivity {
    private TextView inputRegName, inputRegMail, inputPhno, inputUsername;
    private ProgressDialog progressDialog;
    private String name, email, phnNo, username;
    private ImageView imageView;

    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        inputRegName = findViewById(R.id.inputRegName);
        inputRegMail = findViewById(R.id.inputRegMail);
        inputPhno = findViewById(R.id.inputPhno);
        inputUsername = findViewById(R.id.inputUsername);
        progressDialog = new ProgressDialog(this);

//        fAuth = FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser = fAuth.getCurrentUser();

//        if (firebaseUser == null) {
//            Toast.makeText(UserProfile.this, "Something went wrong pls try again", Toast.LENGTH_SHORT).show();
//        } else {
//
//        }
    }
}