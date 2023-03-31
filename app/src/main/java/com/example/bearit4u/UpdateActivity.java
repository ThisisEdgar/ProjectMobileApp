package com.example.bearit4u;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText fname_input, lname_input, address_input, phone_input, email_input, password_input;
    Button update_button;
    Button delete_button;
    DataBaseHelper DB;
    Handler handler = new Handler();

    String fname, lname, address, phone, email, password;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        DB = new DataBaseHelper(this);
        fname_input = findViewById(R.id.etFirstName2);
        lname_input = findViewById(R.id.etLastName2);
        address_input = findViewById(R.id.etAddress2);
        phone_input = findViewById(R.id.etPhone2);
        email_input = findViewById(R.id.etEmail2);
        password_input= findViewById(R.id.etPassword2);
        update_button = findViewById(R.id.btnUpdate);
        delete_button = findViewById(R.id.btnDelete);

        // summon the db instance
        getIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(fname_input.getText().toString(),
                        lname_input.getText().toString(),address_input.getText().toString(),
                        phone_input.getText().toString(), email_input.getText().toString(),
                        password_input.getText().toString());
                // call the method
                DB.updateUser(id, user);

                Toast.makeText(UpdateActivity.this, "user updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.deleteUser(id);

                Toast.makeText(UpdateActivity.this, "user deleted, returning to options screen", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UpdateActivity.this, SpMain.class);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        startActivity(intent);
                    }
                }, 5* 1000);
            }
        });
    }

    void getIntentData(){
        if(getIntent().hasExtra("id") || getIntent().hasExtra("fname") ||
                getIntent().hasExtra("lname") || getIntent().hasExtra("address") ||
        getIntent().hasExtra("phone") || getIntent().hasExtra("email")
        || getIntent().hasExtra("password")){

            // getting data from intent
            id = Integer.parseInt(getIntent().getStringExtra("id"));
            fname = getIntent().getStringExtra("fname");
            lname = getIntent().getStringExtra("lname");
            address = getIntent().getStringExtra("address");
            phone = getIntent().getStringExtra("phone");
            email = getIntent().getStringExtra("email");
            password = getIntent().getStringExtra("password");

            //setting Intent data

            fname_input.setText(fname);
            lname_input.setText(lname);
            address_input.setText(address);
            phone_input.setText(phone);
            email_input.setText(email);
            password_input.setText(password);


        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

    }
}