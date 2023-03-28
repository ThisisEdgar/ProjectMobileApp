package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);

        Button bsearch = findViewById(R.id.btnSearch_MU);
        Button bBook = findViewById(R.id.btnBook_MU);
        Button bVAppointment = findViewById(R.id.btnView_MU);
        Button bService = findViewById(R.id.btnViewService_MU);
        Button bVEdit = findViewById(R.id.btnViewEdit_MU);
        Button bLogout = findViewById(R.id.btnLogout_MU);

        bsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_user.this, SelectCityandDate.class);
                startActivity(intent);
            }
        });
        bBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_user.this, SelectCityandDate.class);
                startActivity(intent);
            }
        });
        bVAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bVEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_user.this, Login_main.class);
                startActivity(intent);
                finish();
            }
        });
    }
}