package com.example.jewelllerylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class register extends AppCompatActivity implements View.OnClickListener {

    private TextView alreadyHaveAccount, btnRegister;
    private EditText inputRegName, inputRegMail, inputPhno, inputUsername, inputRegPassword, inputConfirmpw;
    private ProgressBar progressBar;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fAuth = FirebaseAuth.getInstance();

        alreadyHaveAccount = (TextView) findViewById(R.id.alreadyAcc);
        alreadyHaveAccount.setOnClickListener(this);

        inputRegName = (EditText) findViewById(R.id.inputRegName);
        inputRegMail = (EditText) findViewById(R.id.inputRegMail);
        inputPhno = (EditText) findViewById(R.id.inputPhno);
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputRegPassword = (EditText) findViewById(R.id.inputRegPassword);
        inputConfirmpw = (EditText) findViewById(R.id.inputConfirmpw);

        btnRegister = (TextView) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alreadyAcc:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnRegister:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String name = inputRegName.getText().toString().trim();
        String mail = inputRegMail.getText().toString().trim();
        String phone = inputPhno.getText().toString().trim();
        String username = inputUsername.getText().toString().trim();
        String password = inputRegPassword.getText().toString().trim();
        String confirmPassword = inputConfirmpw.getText().toString().trim();

        if (name.isEmpty()) {
            inputRegName.setError("Full name is required!");
            inputRegName.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            inputPhno.setError("Phone number is required!");
            inputPhno.requestFocus();
            return;
        }
        if (mail.isEmpty()) {
            inputRegMail.setError("Email is required!");
            inputRegMail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            inputRegMail.setError("Please enter a valid email!");
            inputRegMail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            inputRegPassword.setError("Email is required!");
            inputRegPassword.requestFocus();
            return;
        }
        if (password.length() < 3) {
            inputRegPassword.setError("Password must contain at least 6 characters!");
            inputRegPassword.requestFocus();
            return;
        }
        if (username.isEmpty()) {
            inputUsername.setError("Username is required!");
            inputUsername.requestFocus();
            return;
        }
        if (!password.equals(confirmPassword)) {
            inputUsername.setError("Passwords do not match!");
            inputUsername.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        fAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(name, mail, phone, username);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(register.this, "Your account has been created successfully", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(register.this, "Error occurred while creating your account.Please try ", Toast.LENGTH_LONG).show();
                                    }
                                    System.out.println("on completed");
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        } else {
                            Toast.makeText(register.this, "Error occurred while creating your account.Please try again", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });

    }

}