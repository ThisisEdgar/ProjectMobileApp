package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        EditText fn= findViewById(R.id.inputFName_RU);
        EditText ln=findViewById(R.id.inputLN_RU);
        EditText address=findViewById(R.id.inputAddress_RU);
        EditText phone=findViewById(R.id.inputPhone_RU);
        EditText email=findViewById(R.id.inputEmail_RU);
        EditText password= findViewById(R.id.inputPass_RU);
        Button cancel= findViewById(R.id.btncancel_RU);
        Button register = findViewById(R.id.btnRegister_RU);

        //method to add value to database




        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(register_user.this, Login_user.class);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(register_user.this, menu_user.class);
            }
        });
    }
}