package com.example.bearit4u;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

            // validation
            public boolean checkData (){

                CharSequence s = txtFirstName.getText().toString();
                CharSequence t = txtLastName.getText().toString();
                CharSequence r = txtAddress.getText().toString();
                CharSequence i = txtPhone.getText().toString();
                CharSequence n = txtEmail.getText().toString();
                CharSequence g = txtPassword.getText().toString();

                if (TextUtils.isEmpty(s)){

                    Toast.makeText(AddActivity.this, "Please add a First Name", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(t)) {

                    Toast.makeText(AddActivity.this, "Please add a Last Name", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(r)) {

                    Toast.makeText(AddActivity.this, "Please add a Address", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(i)) {

                    Toast.makeText(AddActivity.this, "Please add a Phone", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(n)) {

                    Toast.makeText(AddActivity.this, "Please add a email", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(g)) {

                    Toast.makeText(AddActivity.this, "Please add a Password", Toast.LENGTH_SHORT).show();
                }
            return false; }

            @Override
            public void onClick(View view) {

                if(checkData() == true ){

                    Toast.makeText(AddActivity.this, "error detected", Toast.LENGTH_SHORT).show();
                }else {

                    DB.addUserData(txtFirstName.getText().toString().trim(),
                            txtLastName.getText().toString().trim(),
                            txtAddress.getText().toString().trim(),
                            txtPhone.getText().toString(),
                            txtEmail.getText().toString().trim(),
                            txtPassword.getText().toString()
                    );
                }
            }
        });
    }
}