package com.example.bearit4u;

import static java.lang.Boolean.TRUE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class pickupOrDropOff extends AppCompatActivity
        implements CustomerAdapterEditService.ItemClickListener{
    String date,day,month,year,providerName,user_id,provider_id,pickupOrDropoff, city;
    ArrayList<String> servicesChosen = new ArrayList<>();
    ArrayList<String> services = new ArrayList<>();
    CustomerAdapterEditService adapter;
    DataBaseHelper databaseHelper;
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
        city = intent.getStringExtra("city");
        date= month +"/"+day+"/"+year;
        user_id = intent.getStringExtra("user_id");
        provider_id = intent.getStringExtra("provider_id");
        pickupOrDropoff = intent.getStringExtra("option");

        Button next = findViewById(R.id.btnNext);

        databaseHelper = new DataBaseHelper(this);
        Cursor cursor1 = databaseHelper.viewSPData();

        if(cursor1.getCount()>0){
            while(cursor1.moveToNext()){
                if(cursor1.getInt(0) == Integer.parseInt(provider_id)){
                    String servicesString = cursor1.getString(7);
                    String[] tokens = servicesString.split(",");
                    for(int i = 0; i < tokens.length; i++){
                        services.add(tokens[i]);
                    }
                }
            }
        }
        else{
            Toast.makeText(pickupOrDropOff.this, "nothing to read", Toast.LENGTH_LONG).show();
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerViewService);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomerAdapterEditService(this,services, services,this);
        recyclerView.setAdapter(adapter);

        // Don't forget to send user_id:
        // intent.putExtra("user_id",user_id);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicesChosen = adapter.selectedServices;
                if(servicesChosen.isEmpty()){
                    Toast.makeText(pickupOrDropOff.this, "Please select at least one service", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(pickupOrDropOff.this, confirmation_appointment.class);
                    intent.putExtra("provider", providerName);
                    intent.putExtra("day", day);
                    intent.putExtra("month", month);
                    intent.putExtra("year", year);
                    intent.putExtra("user_id",user_id);
                    intent.putExtra("city",city);
                    intent.putExtra("provider_id",provider_id);
                    intent.putExtra("option",pickupOrDropoff);
                    intent.putExtra("services",servicesChosen);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}