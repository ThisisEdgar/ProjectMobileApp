package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class pickupOrDropOff extends AppCompatActivity {
    String date,day,month,year,providerName,user_id,provider_id,service,pickupOrDropoff;

    RecyclerView recyclerView;
    ArrayList<ServiceModel> arrayList = new ArrayList<>();
    Button btnPickup = findViewById(R.id.btnPickup);
    Button btnDropoff = findViewById(R.id.btnDropoff);

    String[] data = new String[]{"Change Tires", "Oil Change", "Annual Revision", "break revision", "pressure adjust", "Engine setup"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_or_dropoff);
        //get Values from previous activity
        Intent intent = getIntent();
        day = intent.getStringExtra("day");
        month = intent.getStringExtra("month");
        year = intent.getStringExtra("year");
        providerName = intent.getStringExtra("provider");
        date= month +"/"+day+"/"+year;
        user_id = intent.getStringExtra("user_id");
        provider_id= intent.getStringExtra("provider_id");
        //insert values into database
        DataBaseHelper databaseHelper = new DataBaseHelper(this);
        databaseHelper.addAppointment(user_id,date,provider_id,service,pickupOrDropoff);


        recyclerView = findViewById(R.id.recyclerView);
        ServiceCustomAdapter serviceCustomAdapter = new ServiceCustomAdapter(this,getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(serviceCustomAdapter);

       // databaseHelper.addServiceData(date,service);
        // Don't forget to send user_id:
        // intent.putExtra("user_id",user_id);

        btnPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnDropoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    private ArrayList<ServiceModel> getData(){

        for(int i = 0; i < data.length; i++){

            arrayList.add(new ServiceModel(data[i],date.toString() ,false));
        }
        return arrayList;

    }




}