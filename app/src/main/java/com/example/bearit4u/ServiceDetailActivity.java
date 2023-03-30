package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        Button edit = findViewById(R.id.btnEditService);
        Button reminder = findViewById(R.id.btnReminder);
        Button add = findViewById(R.id.btnAddService);
        Button report = findViewById(R.id.btnReport);
        Intent intent = getIntent();
        int sid = intent.getIntExtra("SID", 0);
        int appointment=0;

        Cursor cursor1 = databaseHelper.viewServiceData();

        if(cursor1.getCount()>0){
            while(cursor1.moveToNext()){
                if(cursor1.getInt(0) == sid){
                    appointment =  cursor1.getInt(6);
                    date.setText("Date: "+cursor1.getString(3));
                    services.setText("Services:\n"+cursor1.getString(4));
                    int uid = cursor1.getInt(2);
                    Cursor cursor2 = databaseHelper.viewUserData();
                    while(cursor2.moveToNext()){
                        if(cursor2.getInt(0) == uid){
                            StringBuilder str = new StringBuilder();
                            str.append(cursor2.getString(3));
                            str.append(" " + cursor2.getString(4));
                            name.setText("Customer Name:\n"+str);
                            email.setText("\n"+"Email: "+cursor2.getString(1));
                            phone.setText("Phone: "+cursor2.getString(6));
                        }
                    }
                }
            }
        }
        else{
            Toast.makeText(ServiceDetailActivity.this, "nothing to read", Toast.LENGTH_LONG).show();
        }

        if(appointment == 0){
            report.setVisibility(View.INVISIBLE);
            reminder.setVisibility(View.VISIBLE);
            add.setVisibility(View.VISIBLE);
        }
        if(appointment == 1){
            report.setVisibility(View.VISIBLE);
            reminder.setVisibility(View.INVISIBLE);
            add.setVisibility(View.INVISIBLE);
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceDetailActivity.this,EditServiceActivity.class);
                intent.putExtra("SID", sid);
                startActivity(intent);
                finish();
            }
        });
    }
}