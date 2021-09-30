package com.example.jewelllerylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView createnewAccount;

    EditText inputRegMail, inputRegPassword;
    Button btnLogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth fAuth;
    FirebaseUser fUser;

    private FirebaseUser user;
    private DatabaseReference dbReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        createnewAccount = findViewById(R.id.createAcc);

        inputRegMail = findViewById(R.id.inputRegMail);
        inputRegPassword = findViewById(R.id.inputRegPassword);

        btnLogin = findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);

        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        createnewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, register.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = inputRegMail.getText().toString();
                String password = inputRegPassword.getText().toString();

                if (!mail.matches(emailPattern)) {
                    inputRegMail.setError("Enter a valid Email");
                } else if (password.isEmpty() || password.length() < 6) {
                    inputRegPassword.setError("Password must contain more that 6 characters");
                } else {
                    progressDialog.setMessage("Please Wait a moment for Login....");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    fAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();

                                user = FirebaseAuth.getInstance().getCurrentUser();
                                dbReference = FirebaseDatabase.getInstance().getReference("Users");
                                userId = user.getUid();

                                dbReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        progressDialog.dismiss();

                                        User currentUser = snapshot.getValue(User.class);
                                        System.out.println(currentUser);

                                        if (currentUser != null) {
                                            Intent intent = new Intent(MainActivity.this, UserProfile.class);
                                            intent.putExtra("currentUser", currentUser);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_LONG).show();
                                    }
                                });
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}