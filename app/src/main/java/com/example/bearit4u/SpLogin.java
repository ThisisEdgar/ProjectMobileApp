package com.example.bearit4u;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SpLogin extends AppCompatActivity {
    DataBaseHelper databaseHelper;
    String enteredUsername;
    String enteredPassword;
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> passwords = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_login);
        databaseHelper = new DataBaseHelper(this);




//        databaseHelper.addUserData("troy_kan@hotmail.com", "123456", "Troy", "Kan",
//                "12345 Kingsway", "5873570958");
//        databaseHelper.addSPData("abc", "ABC Car Service", "ab12345c", "600 Royal Ave", "New Westminster",
//                "6041234567", "oil change,car inspection,engine check");
//        databaseHelper.addSPData("bTire", "Burnaby Tire", "qwerty", "7000 Kingsway", "Burnaby",
//                "7785556666", "tire change,car wash");
//        databaseHelper.addServiceData(1,1,"03/30/2023", "car inspection", 1, 1);
//        databaseHelper.addServiceData(2,1,"04/10/2023", "tire change", 0, 0);



       //  databaseHelper.addSPData( "Walmart",
       //  "Walmart","Walmart","Walmart","Kelowna","354654",
      //  "Diagnose");

        // dummy data
        // databaseHelper.addSPData( "Montigo",
        // "Montigo","Montigo","Montigo","Vancouver","354654",
        //"repair");


        EditText username = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Cursor cursor = databaseHelper.viewSPData();
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String usernameStr = cursor.getString(1);
                String passwordStr = cursor.getString(3);
                usernames.add(usernameStr);
                passwords.add(passwordStr);
            }
        }
        else{
            Toast.makeText(SpLogin.this, "no service provider in record", Toast.LENGTH_LONG).show();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText().toString()))
                    Toast.makeText(SpLogin.this,
                            "Please enter username",Toast.LENGTH_LONG).show();
                else if(TextUtils.isEmpty(password.getText().toString()))
                    Toast.makeText(SpLogin.this,
                            "Please enter password",Toast.LENGTH_LONG).show();
                else {
                    enteredUsername = username.getText().toString();
                    enteredPassword = password.getText().toString();
                    if (usernames.contains(enteredUsername)){
                        if(enteredPassword.equals(passwords.get(usernames.indexOf(enteredUsername)))){

                            Intent intent = new Intent(SpLogin.this,SpMain.class);
                            intent.putExtra("SPID", usernames.indexOf(enteredUsername)+1);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(SpLogin.this,
                                    "Wrong password, please re-enter",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(SpLogin.this,
                                "Username does not exist",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}