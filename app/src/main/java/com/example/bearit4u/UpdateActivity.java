package com.example.bearit4u;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText fname_input, lname_input, address_input, phone_input, email_input, password_input;
    Button update_button;

    String id, fname, lname, address, phone, email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        fname_input = findViewById(R.id.etFirstName2);
        lname_input = findViewById(R.id.etLastName2);
        address_input = findViewById(R.id.etAddress2);
        phone_input = findViewById(R.id.etPhone2);
        email_input = findViewById(R.id.etEmail2);
        password_input= findViewById(R.id.etPassword2);
        update_button = findViewById(R.id.btnUpdate);

        // summon the db instance
        getIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper DB = new DatabaseHelper(UpdateActivity.this);

                // call the method
                DB.updateData(id, fname_input.getText().toString(),lname,address, phone,email,password);

            }
        });





    }

    void getIntentData(){
        if(getIntent().hasExtra("id") || getIntent().hasExtra("fname") ||
                getIntent().hasExtra("lname") || getIntent().hasExtra("address") ||
        getIntent().hasExtra("phone") || getIntent().hasExtra("email")
        || getIntent().hasExtra("password")){

            // getting data from intent
            id = getIntent().getStringExtra("id");
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