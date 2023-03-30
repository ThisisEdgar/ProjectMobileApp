package com.example.bearit4u;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpMain extends AppCompatActivity {
    DataBaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_main);

        TextView welcome = findViewById(R.id.txtWelcome);
        Button logout = findViewById(R.id.btnLogout);
        Button user = findViewById(R.id.btnUserInfo);
        Button service = findViewById(R.id.btnServiceInfo);
        Button appointment = findViewById(R.id.btnAppointment);
        String loginSP="";

        Intent intent = getIntent();
        int spid = intent.getIntExtra("SPID", 0);
        databaseHelper = new DataBaseHelper(this);
        Cursor spCursor = databaseHelper.viewSPData();
        if(spCursor.getCount()>0){
            while(spCursor.moveToNext()){
                if(Integer.toString(spid).equals(spCursor.getString(0))) {
                    loginSP = spCursor.getString(1);
                }
            }
        }

        welcome.setText("Welcome! " + loginSP);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpMain.this,Login_main.class));
                finish();
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SpMain.this,ShowUsers.class));
            }
        });

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpMain.this,SpServiceActivity.class);
                intent.putExtra("SPID", spid);
                intent.putExtra("APPOINT", 1);
                startActivity(intent);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpMain.this,SpServiceActivity.class);
                intent.putExtra("SPID", spid);
                intent.putExtra("APPOINT", 0);
                startActivity(intent);
            }
        });
    }
}