package com.example.bearit4u;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SpLogin extends AppCompatActivity {
    DataBaseHelper2 databaseHelper;
    String enteredUsername;
    String enteredPassword;
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> passwords = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_login);
        databaseHelper = new DataBaseHelper2(this);

        EditText username = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Cursor cursor = databaseHelper.viewSPData();
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String usernameStr = cursor.getString(1);
                String passwordStr = cursor.getString(3);
                usernames.add(usernameStr);
                passwords.add(passwordStr);
            }
        }
        else{
            Toast.makeText(SpLogin.this, "no service provider in record", Toast.LENGTH_LONG).show();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText().toString()))
                    Toast.makeText(SpLogin.this,
                            "Please enter username",Toast.LENGTH_LONG).show();
                else if(TextUtils.isEmpty(password.getText().toString()))
                    Toast.makeText(SpLogin.this,
                            "Please enter password",Toast.LENGTH_LONG).show();
                else {
                    enteredUsername = username.getText().toString();
                    enteredPassword = password.getText().toString();
                    if (usernames.contains(enteredUsername)){
                        if(enteredPassword.equals(passwords.get(usernames.indexOf(enteredUsername)))){

                            Intent intent = new Intent(SpLogin.this,SpMain.class);
                            intent.putExtra("USERNAME", enteredUsername);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(SpLogin.this,
                                    "Wrong password, please re-enter",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(SpLogin.this,
                                "Username does not exist",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}