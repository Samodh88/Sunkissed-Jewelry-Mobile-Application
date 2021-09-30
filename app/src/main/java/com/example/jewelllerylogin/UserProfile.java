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

        inputRegName = findViewById(R.id.inputRegName);
        inputRegMail = findViewById(R.id.inputRegMail);
        inputPhno = findViewById(R.id.inputPhno);
        inputUsername = findViewById(R.id.inputUsername);
        progressDialog = new ProgressDialog(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        dbReference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        dbReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User currentUser = snapshot.getValue(User.class);
                progressDialog.dismiss();

                if (currentUser != null) {
                    String name = currentUser.name;
                    String email = currentUser.mail;
                    String phone = currentUser.phone;
                    String userName = currentUser.username;

                    inputRegName.setText(name);
                    inputRegMail.setText(email);
                    inputPhno.setText(phone);
                    inputUsername.setText(userName);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this, "Error!", Toast.LENGTH_LONG).show();
            }

        });

    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditProf:
                startActivity(new Intent(this, Edit.class));
                break;

        }
    }
}