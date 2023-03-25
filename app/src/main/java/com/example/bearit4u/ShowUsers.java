package com.example.bearit4u;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShowUsers extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btnAdd;

    DataBaseHelper DB;
    ArrayList<String> user_id, firstName, lastName, address, phone, email, password;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showusers);

        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd_ASU);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowUsers.this,AddActivity.class);
                startActivity(intent);
            }
        });

        DB = new DataBaseHelper(this);

        user_id = new ArrayList<>();
        firstName = new ArrayList<>();
        lastName= new ArrayList<>();
        address= new ArrayList<>();
        phone= new ArrayList<>();
        email= new ArrayList<>();
        password= new ArrayList<>();


        storeDataArrays();

        customAdapter=new CustomAdapter(ShowUsers.this,this, user_id,
                firstName,lastName, address,phone,email,password);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowUsers.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){

            recreate();
        }
    }

    void storeDataArrays(){

        Cursor cursor = DB.viewUserData();
        if(cursor.getCount()== 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        }else {
            while(cursor.moveToNext()){

                user_id.add(cursor.getString(0));
                firstName.add(cursor.getString(1));
                lastName.add(cursor.getString(2));
                address.add(cursor.getString(3));
                phone.add(cursor.getString(4));
                email.add(cursor.getString(5));
                password.add(cursor.getString(6));
            }
        }

    }
}