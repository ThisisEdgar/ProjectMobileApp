package com.example.bearit4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText txtFirstName, txtLastName, txtAddress, txtPhone, txtEmail, txtPassword;
    Button btnAddUser;
    DataBaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        DB = new DataBaseHelper(this);
        txtFirstName = findViewById(R.id.etFirstName);
        txtLastName = findViewById(R.id.etLastName);
        txtAddress = findViewById(R.id.etAddress);
        txtPhone =  findViewById(R.id.etPhone);
        txtEmail = findViewById(R.id.etEmail);
        txtPassword = findViewById(R.id.etPassword);
        btnAddUser = findViewById(R.id.btnAddUser_AA);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DB.addUserData(txtFirstName.getText().toString().trim(),
                        txtLastName.getText().toString().trim(),
                        txtAddress.getText().toString().trim(),
                        txtPhone.getText().toString(),
                        txtEmail.getText().toString().trim(),
                        txtPassword.getText().toString()


                );
            }
        });
    }
}