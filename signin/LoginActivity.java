package com.example.signup;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bundle bundle = getIntent().getExtras();
        final String receivedUsername = bundle.getString("username");
        final String receivedPassword = bundle.getString("password");

        final Button submit = findViewById(R.id.signin);
        submit.setOnClickListener(v -> {
            EditText t1 = LoginActivity.this.findViewById(R.id.Username);
            EditText t2 = LoginActivity.this.findViewById(R.id.Password);
            String s1 = t1.getText().toString().trim();
            String s2 = t2.getText().toString().trim();
            if (s1.equals(receivedUsername) && s2.equals(receivedPassword)) {
                Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_LONG).show();
            } else {
                counter++;

                if (counter == 1) {
                    Toast.makeText(LoginActivity.this, "Invalid Username/Password!!2 Attempts Left", Toast.LENGTH_SHORT).show();
                }
                if (counter == 2) {
                    Toast.makeText(LoginActivity.this, "Invalid Username/Password!!1 Attempt Left", Toast.LENGTH_SHORT).show();
                }
                if (counter == 3) {
                    Toast.makeText(LoginActivity.this, "Login Attempt Denied!!", Toast.LENGTH_SHORT).show();
                    submit.setEnabled(false);
                }
            }
        });
    }
}