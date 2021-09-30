package com.example.jewelllerylogin;

import androidx.annotation.NonNull;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit extends AppCompatActivity {
    TextInputLayout inputRegName, inputRegMail, inputRegPhno, inputUsername;
    String _NAME, _EMAIL, _PHONENO, _USERNAME;

    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dbReference = FirebaseDatabase.getInstance().getReference("Users");

        inputRegName = findViewById(R.id.inputRegName);
        inputRegMail = findViewById(R.id.inputRegMail);
        inputRegPhno = findViewById(R.id.inputRegPhno);
        inputUsername = findViewById(R.id.inputUsername);

        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
        _NAME = intent.getStringExtra("name");
        _PHONENO = intent.getStringExtra("phoneNo");
        _EMAIL = intent.getStringExtra("email");

        inputRegName.getEditText().setText(_NAME);
        inputRegMail.getEditText().setText(_EMAIL);
        inputRegPhno.getEditText().setText(_PHONENO);
        inputUsername.getEditText().setText(_USERNAME);


    }

    public void update(View view) {
        if (isNameChanged() || isUsernameChanged() || isPhoneNoChanged() || isEmailChanged()) {
            Toast.makeText(this, "Data has been Updated", Toast.LENGTH_LONG).show();

        }
        else Toast.makeText(this, "Data is same and cannot be updated", Toast.LENGTH_SHORT).show();
    }

    private boolean isPhoneNoChanged() {
        if(!_PHONENO.equals(inputRegPhno.getEditText().getText().toString())){
            dbReference.child(_EMAIL).child("phone").setValue(inputRegPhno.getEditText().getText().toString());
            return true;
        }else{
            return false;
        }
    }


    private boolean isEmailChanged() {

        if(!_EMAIL.equals(inputRegMail.getEditText().getText().toString())){
            dbReference.child(_EMAIL).child("mail").setValue(inputRegMail.getEditText().getText().toString());
            return true;
        }else{
            return false;
        }

    }

    private boolean isUsernameChanged() {
        if(!_USERNAME.equals(inputUsername.getEditText().getText().toString())){
            dbReference.child(_EMAIL).child("username").setValue(inputUsername.getEditText().getText().toString());
            return true;
        }else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!_NAME.equals(inputRegName.getEditText().getText().toString())){
            dbReference.child(_EMAIL).child("name").setValue(inputRegName.getEditText().getText().toString());
            return true;
        }else{
            return false;
        }
    }



}