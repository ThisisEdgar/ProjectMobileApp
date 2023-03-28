package com.example.bearit4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectCityandDate extends AppCompatActivity {
    //Step 1 get all the cities into the array
    ArrayList<String> cities = new ArrayList<>();
    Spinner city;
    Integer day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cityand_date);
        //adding random data
        DataBaseHelper sqLiteDatabase = new DataBaseHelper(this);
        sqLiteDatabase.addSPData("username", "name", "password", "address", "city", "phone", "services");


        //Step 2. Get all the values from database
        DataBaseHelper databaseHelper = new DataBaseHelper(this);
        Cursor cursor = databaseHelper.viewSPData();

        //Gets only the cities from Service Provider table
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String allCities = cursor.getString(5); //Number of column 5
                cities.add(allCities);
            }
        }

        city = findViewById(R.id.spinnerDrop);
        Button button = findViewById(R.id.btnSCD);
        //Step 3. Populate the spinner with the values you have
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                cities);
        city.setAdapter(adapter);
        //Step 4. Get the date
        CalendarView calendarView = findViewById(R.id.calendarAppointment);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int cyear, int cmonth, int dayOfMonth) {
                day = dayOfMonth;
                month = cmonth + 1; // month starts in 0 for some reason
                year = cyear;
            }
        });

        //Step . Send the selection to the next activity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedCity = city.getSelectedItem().toString();
                if (TextUtils.isEmpty(selectedCity) || day == null || month == null || year == null) {
                    Toast.makeText(SelectCityandDate.this, "Please select a city and a date for your appointment", Toast.LENGTH_LONG).show();
                } else {
                    //Test if it works
                    Toast.makeText(SelectCityandDate.this, "Day selected " + day + " of " + month + " of " + year, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SelectCityandDate.this, searchOrBookUser.class);
                    intent.putExtra("city", selectedCity);
                    intent.putExtra("day", day);
                    intent.putExtra("month", month);
                    intent.putExtra("year", year);
                    startActivity(intent);
                }
            }
        });
    }
}
