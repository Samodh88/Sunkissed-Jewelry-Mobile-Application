package com.example.jewelllerylogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class UserProfile extends AppCompatActivity {
    private TextView inputRegName, inputRegMail, inputPhno, inputUsername;
    private ProgressDialog progressDialog;
    private String name, email, phnNo, username;
    private ImageView imageView;
    private Button btnEditProf;

    private FirebaseAuth fAuth;

    private FirebaseUser user;
    private DatabaseReference dbReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        inputRegName = findViewById(R.id.inputRegName);
        inputRegMail = findViewById(R.id.inputRegMail);
        inputPhno = findViewById(R.id.inputPhno);
        inputUsername = findViewById(R.id.inputUsername);
        progressDialog = new ProgressDialog(this);

        // get the current user as a serializable object
        User currentUser = (User) this.getIntent().getSerializableExtra("currentUser");

        System.out.println(currentUser);

        inputRegName.setText(currentUser.name);
        inputRegMail.setText(currentUser.mail);
        inputPhno.setText(currentUser.phone);
        inputUsername.setText(currentUser.username);
    }

    public void test(View view) {
        // get the current user as a serializable object
        User currentUser = (User) this.getIntent().getSerializableExtra("currentUser");

        Intent intent = new Intent(this, Edit.class);
        intent.putExtra("currentUser", currentUser);
        startActivity(intent);
    }
}