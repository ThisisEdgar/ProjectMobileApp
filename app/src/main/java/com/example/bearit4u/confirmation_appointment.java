package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class confirmation_appointment extends AppCompatActivity {
    String myServices,day,month,year,selectedCity,user_id,provider_id,option,date;
    int db_option;
    TextView tday,tmonth,tyear,tCity,toption,tservices,tprovider;
    ArrayList<String> services;
    Button confirm,edit;
    DataBaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_appointment);
        databaseHelper = new DataBaseHelper(this);

        //get intents
        Intent intent = new Intent();
        intent = getIntent();
        day = intent.getStringExtra("day");
        month = intent.getStringExtra("month");
        year = intent.getStringExtra("year");
        selectedCity= intent.getStringExtra("city");
        user_id = intent.getStringExtra("user_id");
        provider_id = intent.getStringExtra("provider_id");
        option=intent.getStringExtra("option");
        services = intent.getStringArrayListExtra("services");

        //initialize TextViews
        tday =findViewById(R.id.txtDay_CA);
        tmonth =findViewById(R.id.txtMonth_CA);
        tyear =findViewById(R.id.txtYear_CA);
        tCity =findViewById(R.id.txtCity_CA);
        toption =findViewById(R.id.txtOption_CA);
        tservices =findViewById(R.id.txtServices_CA);
        tprovider = findViewById(R.id.txtProvider_CA);

        //set Text
        tday.setText("Day: " +day);
        tmonth.setText("Month: " +month);
        tyear.setText("Year: "+year);
        tCity.setText("City: "+selectedCity);
        toption.setText("Pickup or dropoff: "+option);


        //Add to string for each value in arrayList
        myServices = "";
        for (int i = 0; i < services.size(); i++){
            if(i == services.size()-1)
                myServices += services.get(i);
            else
                myServices += services.get(i) + ",";
        }
        tservices.setText(myServices);
        date = day + "/" + month + "/" + year;
        if(option.equals("pickup"))
        {
            db_option = 0;
        }
        else if(option.equals("dropoff"))
        {
            db_option = 1;
        }

        //initialize the buttons
        edit= findViewById(R.id.btnEdit_CA);
        confirm = findViewById(R.id.btnConfirm_CA);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //return user to the first Appointment activity
            Intent intent1 = new Intent(confirmation_appointment.this, SelectCityandDate.class);
            intent1.putExtra("user_id",user_id);
            startActivity(intent1);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //insert values into database

                databaseHelper.addServiceData(Integer.parseInt(provider_id), Integer.parseInt(user_id),
                        date, myServices, db_option, 0);
                //send to menu_user
                Intent intent = new Intent(confirmation_appointment.this, menu_user.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
                //Show a toast with appointment added
                Toast.makeText(confirmation_appointment.this, "Appointment added"
                        ,Toast.LENGTH_LONG).show();
            }
        });
    }
}