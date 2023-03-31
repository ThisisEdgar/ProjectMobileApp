package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class menu_user extends AppCompatActivity {
    String userName,userId, user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);

        Button bBook = findViewById(R.id.btnBook_MU);
        Button bVAppointment = findViewById(R.id.btnView_MU);
        Button bService = findViewById(R.id.btnViewService_MU);
        Button bVEdit = findViewById(R.id.btnViewEdit_MU);
        Button bLogout = findViewById(R.id.btnLogout_MU);
        Intent intent =getIntent();


        if (intent.hasExtra("userName")) {
            String userName = intent.getStringExtra("userName");
            if (!userName.isEmpty()) {
                // get only user
                DataBaseHelper databaseHelper = new DataBaseHelper(this);
                Cursor cursor = databaseHelper.searchName(userName);
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        userId = cursor.getString(0); //Number of column 0 user id
                    }
                }
            }
        } else if (intent.hasExtra("user_id")) {
            String user_id = intent.getStringExtra("user_id");
            if (!user_id.isEmpty()) {
                DataBaseHelper DB = new DataBaseHelper(this);
                Cursor cursor = DB.viewSingleUserData(Integer.parseInt(user_id));
                if (cursor.moveToFirst()) {
                    userId = cursor.getString(cursor.getColumnIndexOrThrow("FirstName")); //Number of column 2
                }
            }
        }



        bBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_user.this, SelectCityandDate.class);
                intent.putExtra("user_id",userId);
                startActivity(intent);
            }
        });
        bVAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(menu_user.this, Login_main.class);
//                intent.putExtra("user_id",userId);
//                startActivity(intent);
            }
        });
        bService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(menu_user.this, Login_main.class);
//                intent.putExtra("user_id",userId);
//                startActivity(intent);
            }
        });
        bVEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(menu_user.this, "Send user "+userId
                 //       ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(menu_user.this, ViewSingleUser.class);
                intent.putExtra("user_id",userId);

                startActivity(intent);
            }
        });
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(menu_user.this, Login_main.class);
                intent.putExtra("user_id",userId);

                startActivity(intent);
                finish();
            }
        });
    }
}