package com.example.bearit4u;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SPUserActivity extends AppCompatActivity
        implements CustomAdapter2.ItemClickListener{

    CustomAdapter2 adapter;
    DataBaseHelper2 databaseHelper;
    ArrayList<String> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spuser);
        databaseHelper = new DataBaseHelper2(this);

        Cursor cursor = databaseHelper.viewUserData();
        StringBuilder str = new StringBuilder();
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                str.append(cursor.getString(3));
                str.append(" " + cursor.getString(4));
                str.append("\n");
                str.append(cursor.getString(1));
                users.add(String.valueOf(str));
                str.delete(0, str.length());
            }
        }
        else{
            Toast.makeText(SPUserActivity.this, "nothing to read", Toast.LENGTH_LONG).show();
        }


        RecyclerView recyclerView = findViewById(R.id.spUserRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomAdapter2(this,users,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}