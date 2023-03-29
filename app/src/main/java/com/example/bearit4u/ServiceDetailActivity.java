package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class ServiceDetailActivity extends AppCompatActivity {
    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        databaseHelper = new DataBaseHelper(this);
        TextView name = findViewById(R.id.txtUserName);
        TextView email = findViewById(R.id.txtEmail);
        TextView phone = findViewById(R.id.txtPhone);
        TextView date = findViewById(R.id.txtAppointDate);
        TextView services = findViewById(R.id.txtServices);
        Intent intent = getIntent();
        int sid = intent.getIntExtra("SID", 0);

        Cursor cursor1 = databaseHelper.viewServiceData();
    }
}