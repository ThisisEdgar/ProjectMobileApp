package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;

public class register_user extends AppCompatActivity {
    DataBaseHelper DB;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = new DataBaseHelper(this);
        setContentView(R.layout.activity_register_user);
        EditText fn = findViewById(R.id.inputFName_RU);
        EditText ln = findViewById(R.id.inputLN_RU);
        EditText address = findViewById(R.id.inputAddress_RU);
        EditText phone = findViewById(R.id.inputPhone_RU);
        EditText email = findViewById(R.id.inputEmail_RU);
        EditText password = findViewById(R.id.inputPass_RU);
        Button cancel = findViewById(R.id.btncancel_RU);
        Button register = findViewById(R.id.btnRegister_RU);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {

            // validation
            public boolean checkData() {

                CharSequence s = fn.getText().toString();
                CharSequence t = ln.getText().toString();
                CharSequence r = address.getText().toString();
                CharSequence i = phone.getText().toString();
                CharSequence n = email.getText().toString();
                CharSequence g = password.getText().toString();

                if (TextUtils.isEmpty(s)) {

                    Toast.makeText(register_user.this, "Please add a First Name", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (TextUtils.isEmpty(t)) {

                    Toast.makeText(register_user.this, "Please add a Last Name", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (TextUtils.isEmpty(r)) {

                    Toast.makeText(register_user.this, "Please add a Address", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (TextUtils.isEmpty(i)) {

                    Toast.makeText(register_user.this, "Please add a Phone", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (TextUtils.isEmpty(n)) {

                    Toast.makeText(register_user.this, "Please add a email", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (TextUtils.isEmpty(g)) {

                    Toast.makeText(register_user.this, "Please add a Password", Toast.LENGTH_SHORT).show();
                    return true;
                }else
                return false;
            }

            @Override
            public void onClick(View view) {
                ArrayList<String> names = new ArrayList<>();

                if (checkData() == true) {

                    Toast.makeText(register_user.this, "error detected", Toast.LENGTH_SHORT).show();

                } else {
                    DataBaseHelper databaseHelper = new DataBaseHelper(register_user.this);
                    Cursor cursor = databaseHelper.viewUserData();
                    String usernames;
                    if (cursor.getCount() > 0) {
                        while (cursor.moveToNext()) {
                             usernames = cursor.getString(1); //Number of column 5
                            names.add(usernames);

                        }
                    }
                    if (names.contains(fn.getText().toString()))
                    {
                        Toast.makeText(register_user.this, "Username already in use", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        DB.addUserData(
                                fn.getText().toString().trim(),
                                ln.getText().toString().trim(),
                                address.getText().toString().trim(),
                                phone.getText().toString(),
                                email.getText().toString().trim(),
                                password.getText().toString()
                        );

                        Toast.makeText(register_user.this, "Register success!, you will return to the previous screen", Toast.LENGTH_SHORT).show();

                        handler.postDelayed(new Runnable() {
                            public void run() {
                                finish();
                            }
                        }, 5* 1000);
                    }


                }
            }
        });
    }


}