package com.example.jewelllerylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        User currentUser = (User)this.getIntent().getSerializableExtra("currentUser");
        inputRegName = findViewById(R.id.inputRegName);
        inputRegMail = findViewById(R.id.inputRegMail);
        inputPhno = findViewById(R.id.inputPhno);
        inputUsername = findViewById(R.id.inputUsername);
        progressDialog = new ProgressDialog(this);

        System.out.println(currentUser);

        inputRegName.setText(currentUser.getName());
        inputRegMail.setText(currentUser.getMail());
        inputPhno.setText(currentUser.getPhone());
        inputUsername.setText(currentUser.getUsername());

    }
    public void test(View view) {
        Intent intent = new Intent(this, Edit.class);
        startActivity(intent);;


    }
}