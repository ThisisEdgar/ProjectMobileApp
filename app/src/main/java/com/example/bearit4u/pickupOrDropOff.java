package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

public class pickupOrDropOff extends AppCompatActivity {
    String date,day,month,year,providerName,user_id,provider_id,service,pickupOrDropoff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_or_drop_off);
        //get Values from previous activity
        Intent intent = getIntent();
        day = intent.getStringExtra("day");
        month = intent.getStringExtra("month");
        year = intent.getStringExtra("year");
        providerName = intent.getStringExtra("provider");
        date= month +"/"+day+"/"+year;
        user_id = intent.getStringExtra("user_id");
        provider_id= intent.getStringExtra("provider_id");
        pickupOrDropoff = intent.getStringExtra("option");
        //insert values into database
        DataBaseHelper databaseHelper = new DataBaseHelper(this);
        databaseHelper.addAppointment(user_id,date,provider_id,service,pickupOrDropoff);

        // Don't forget to send user_id:
        // intent.putExtra("user_id",user_id);
    }
}