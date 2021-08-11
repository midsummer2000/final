package com.example.signup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signup = findViewById(R.id.signup);
        signup.setOnClickListener(v -> {
            EditText username, password;
            username = findViewById(R.id.username);
            password = findViewById(R.id.password);
            String usrn = username.getText().toString();
            String pswrd = password.getText().toString();

            if (checkString(pswrd)) {
                Toast.makeText(MainActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("username", usrn);
                intent.putExtra("password", pswrd);
                startActivity(intent);
            } else {
                String msg = "Password Should contain 1 Capital Letter\n 1 Small Letter\n 1 Number\n 1 Special Character\n Min 8 Length";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

        });
    }
    static boolean checkString(String str) {
        char ch;
        boolean upperCaseFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        boolean specialFlag = false;

        if(str.length() < 8)
            return  false;

        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                upperCaseFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            } else if (ch == '#' || ch == '@' || ch == '%' || ch == '_') {
                specialFlag = true;
            }
            if (numberFlag && upperCaseFlag && lowerCaseFlag && specialFlag)
                return true;
        }
        return false;
    }
}