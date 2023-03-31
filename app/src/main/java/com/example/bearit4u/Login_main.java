package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_main extends AppCompatActivity {
    // DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        Button button1 = findViewById(R.id.btnUser_LM);
        Button button2 = findViewById(R.id.SP_LM);

//        DataBaseHelper  databaseHelper= new DataBaseHelper(this);
//        databaseHelper.addSPData("Carstar", "Carstar Autobody Repair", "csabr", "600 Royal Ave", "New Westminster",
//               "6041234567", "oil change,car inspection,engine check");
//        databaseHelper.addSPData("Amin", "Amin's Auto Service", "aas", "204 Kingsway", "Vancouver",
//                "604165998", "tire change,engine recycle, Serpentine belt inspection,Wiper blade inspection");
//
//        databaseHelper.addSPData("Gerry", "Gerry's OK Tire", "got", "6480 Riverside Street", "Mission",
//                 "6048260519", " Suspension Services,Battery Services,Wiper blade inspection");
//
//        databaseHelper.addSPData("Perkin", "Perkin's Automotive", "pam", "552 ocean view", "Maple Ridge",
//                "604823358", " Caravan revision,insurance repair, accident parts");
//
//        databaseHelper.addSPData("Tireland", "Tireland", "ttt", "Albion Street", "Maple Ridge",
//                "602528833", " Tires change, tires thickness check, tires repair, tires sales, tires recycle");
//
//        databaseHelper.addSPData("Intune", "Intune AutoRepairs", "iar", "41865 Mclean Ave", "Coquitlam",
//                "645223663", " Sound system adjust, Buffer design,Battery replacement ");
//
//        databaseHelper.addSPData("Austin", "Austin Automotive", "aam", "236 gaglardy way", "Burnaby",
//                "644836694", " NO oxide, Transmision, mirrors reescalation, Caravan revision  ");
//
//        databaseHelper.addSPData("WestRich", "West Rich Auto Services", "wras", "12091 Vulcan Way", "Richmond",
//                "687966596", " impossible recoveries, towing");
//
//        databaseHelper.addSPData("Minit", "Minit-Tune & Brake Auto Centres", "mtbac", "12094 Vulcan Way", "Richmond",
//                "687964596", " Brake adjust, pit station, fluids");
//
//        databaseHelper.addSPData("Ambassador", "Ambassador Auto Repairs", "aar", "fleetwood street", "Surrey",
//                "687222536", " accidents, insurance, transmision, tires, sound system adjust, fluids, brakes");
//===================================================================================================================
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login_main.this, Login_user.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login_main.this, SpLogin.class);
                startActivity(intent);
            }
        });
    }
}