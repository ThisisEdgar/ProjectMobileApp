package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class enterLogin_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_login_user);
        Button button1= findViewById(R.id.btnLogin_ELI);
        Button button2=findViewById(R.id.btnCancel_ELI);
        EditText editText1= findViewById(R.id.inputUser_ELI);
        EditText editText2 = findViewById(R.id.inputPassword_ELI);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent= new Intent(enterLogin_user.this, menu_user.class);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(enterLogin_user.this, Login_user.class);
            }
        });

    }
}