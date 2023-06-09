package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



public class searchOrBookUser extends AppCompatActivity  implements CustomAdapterSearch.ItemClickListener{
    Integer pos;
    String[] provider;
    String[] provider_ids;
    String day,month,year,selectedCity,selectedProvider,user_id,provider_id,option;
    Button btnbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_or_book_user);


        //get the date and the city
        Intent intent = new Intent(getIntent());
        if (intent != null)
        {
            day = intent.getStringExtra("day");
            month = intent.getStringExtra("month");
            year = intent.getStringExtra("year");
            selectedCity= intent.getStringExtra("city");
            user_id = intent.getStringExtra("user_id");
        }
        //Fill the array cities with all the cities
        DataBaseHelper databaseHelper = new DataBaseHelper(this);
        Cursor cursor = databaseHelper.viewProvidersByCity(selectedCity);
        Integer numberOfarray =0;
        provider = new String[cursor.getCount()];
        provider_ids = new String[cursor.getCount()];
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(2);
//                provider_id = cursor.getString(0);
                provider_ids[numberOfarray] = Integer.toString(cursor.getInt(0));
                provider[numberOfarray] = name;
                numberOfarray++;
            }
        }
        RecyclerView recyclerView = findViewById(R.id.providerList);
        CustomAdapterSearch customAdapterSearch = new CustomAdapterSearch(this,provider,this);
        Integer numOfCols =1;
        recyclerView.setLayoutManager(new GridLayoutManager(this,numOfCols));
        recyclerView.setAdapter(customAdapterSearch);
        //picup or dropoff
        RadioButton pickup = findViewById(R.id.radiobtnPickup);
        RadioButton dropoff = findViewById(R.id.radiobtnDropoff);

        btnbook = findViewById(R.id.btnBook_SBU);
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pos != null && selectedProvider != null){
                    if(pickup.isChecked())
                        option = "pickup";
                    else
                        option = "dropoff";
                    Intent intent = new Intent(searchOrBookUser.this, pickupOrDropOff.class);
                    intent.putExtra("provider", provider[pos]);
                    intent.putExtra("day", day);
                    intent.putExtra("month", month);
                    intent.putExtra("year", year);
                    intent.putExtra("city", selectedCity);
                    intent.putExtra("user_id",user_id);
                    intent.putExtra("provider_id",provider_ids[pos]);
                    intent.putExtra("option",option);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(searchOrBookUser.this,"Please select a provider", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    // Check this
    @Override
    public void onItemClick(View view, Integer pos) {
        //Create pickupOrDropOff
        selectedProvider = provider[pos];
        this.pos = pos;
        Toast.makeText(searchOrBookUser.this, "Selected Provider: " +provider[pos]+ " date: "+month+"/"+day+"/"+year
                ,Toast.LENGTH_LONG).show();

    }

}