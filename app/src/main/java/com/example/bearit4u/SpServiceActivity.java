package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SpServiceActivity extends AppCompatActivity
        implements CustomAdapterSpService.ItemClickListener{

    CustomAdapterSpService adapter;
    DataBaseHelper databaseHelper;
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<String> users = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    int appointment = 0;
    int source = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_service);

        TextView name = findViewById(R.id.textView4);
        databaseHelper = new DataBaseHelper(this);
        Intent intent = getIntent();
        int spid = intent.getIntExtra("SPID", 0);
        int uid = intent.getIntExtra("UID", 0);
        appointment = intent.getIntExtra("APPOINT", 0);

        if(spid != 0) {
            name.setText("Customer Info");
            source = 0;
            Cursor cursor1 = databaseHelper.viewServiceData();

            StringBuilder str = new StringBuilder();
            if (cursor1.getCount() > 0) {
                while (cursor1.moveToNext()) {
                    if (cursor1.getInt(1) == spid && cursor1.getInt(6) == appointment) {
                        ids.add(cursor1.getInt(0));
                        dates.add(cursor1.getString(3));
                        uid = cursor1.getInt(2);
                        Cursor cursor2 = databaseHelper.viewUserData();
                        while (cursor2.moveToNext()) {
                            if (cursor2.getInt(0) == uid) {
                                str.append(cursor2.getString(1));
                                str.append(" " + cursor2.getString(2));
                                str.append("\n");
                                str.append(cursor2.getString(5));
                                users.add(String.valueOf(str));
                                str.delete(0, str.length());
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(SpServiceActivity.this, "nothing to read", Toast.LENGTH_LONG).show();
            }
        }
        else if (uid !=0){
            name.setText("Service Provider Info");
            source = 1;
            Cursor cursor1 = databaseHelper.viewServiceData();

            StringBuilder str = new StringBuilder();
            if (cursor1.getCount() > 0) {
                while (cursor1.moveToNext()) {
                    if (cursor1.getInt(2) == uid && cursor1.getInt(6) == appointment) {
                        ids.add(cursor1.getInt(0));
                        dates.add(cursor1.getString(3));
                        spid = cursor1.getInt(1);
                        Cursor cursor2 = databaseHelper.viewSPData();
                        while (cursor2.moveToNext()) {
                            if (cursor2.getInt(0) == spid) {
                                str.append(cursor2.getString(2));
                                str.append("\n");
                                str.append(cursor2.getString(4)+", "+cursor2.getString(5));
                                users.add(String.valueOf(str));
                                str.delete(0, str.length());
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(SpServiceActivity.this, "nothing to read", Toast.LENGTH_LONG).show();
            }
        }


        RecyclerView recyclerView = findViewById(R.id.spServiceRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomAdapterSpService(this,ids, users, dates,this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(SpServiceActivity.this,ServiceDetailActivity.class);
        intent.putExtra("SID", ids.get(position));
        intent.putExtra("SOURCE", source);
        startActivity(intent);
        finish();
    }
}