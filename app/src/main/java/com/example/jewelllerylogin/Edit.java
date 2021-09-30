package com.example.jewelllerylogin;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit extends AppCompatActivity {
    TextView inputRegName, inputRegMail, inputRegPhone, inputUsername;

    private FirebaseUser user;
    private DatabaseReference dbReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // get the current user as a serializable object
        User currentUser = (User) this.getIntent().getSerializableExtra("currentUser");

        user = FirebaseAuth.getInstance().getCurrentUser();
        dbReference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        inputRegName = findViewById(R.id.inputRegName);
        inputRegMail = findViewById(R.id.inputRegMail);
        inputRegPhone = findViewById(R.id.inputRegPhno);
        inputUsername = findViewById(R.id.inputUsername);

        inputRegName.setText(currentUser.name);
        inputRegMail.setText(currentUser.mail);
        inputRegPhone.setText(currentUser.phone);
        inputUsername.setText(currentUser.username);
    }

    public void update(View view) {
        dbReference = FirebaseDatabase.getInstance().getReference("Users");

        User editedUser = new User(
                inputRegName.getText().toString(),
                inputRegMail.getText().toString(),
                inputRegPhone.getText().toString(),
                inputUsername.getText().toString()
        );

        dbReference.child(userId).setValue(editedUser).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(Edit.this, "Updated", Toast.LENGTH_LONG);
            }
        });
    }
}