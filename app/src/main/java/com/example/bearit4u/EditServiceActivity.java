package com.example.bearit4u;

import static java.lang.Boolean.TRUE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditServiceActivity extends AppCompatActivity
        implements CustomerAdapterEditService.ItemClickListener{
    DataBaseHelper databaseHelper;
    CustomerAdapterEditService adapter;
    ArrayList<String> services = new ArrayList<>();
    ArrayList<String> selectedServices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);
        TextView userInfo = findViewById(R.id.txtUserInfo);
        TextView sidText = findViewById(R.id.txtSid);
        EditText date = findViewById(R.id.editTextDate);
        RadioButton pickup = findViewById(R.id.radioPickup);
        RadioButton dropin = findViewById(R.id.radioDropIn);

        Intent intent = getIntent();
        int sid = intent.getIntExtra("SID", 0);
        databaseHelper = new DataBaseHelper(this);
        Cursor cursor1 = databaseHelper.viewServiceData();

        if(cursor1.getCount()>0){
            while(cursor1.moveToNext()){
                if(cursor1.getInt(0) == sid){
                    sidText.setText("Service ID: " + sid);
                    date.setText(cursor1.getString(3));
                    String servicesString = cursor1.getString(4);
                    String[] tokens = servicesString.split(",");
                    for(int i = 0; i < tokens.length; i++){
                        selectedServices.add(tokens[i]);
                    }
                    int uid = cursor1.getInt(2);
                    int spid = cursor1.getInt(1);
                    if(cursor1.getInt(5) == 0)
                        pickup.setChecked(TRUE);
                    else
                        dropin.setChecked(TRUE);
                    Cursor cursor2 = databaseHelper.viewUserData();
                    while(cursor2.moveToNext()){
                        if(cursor2.getInt(0) == uid){
                            StringBuilder str = new StringBuilder();
                            str.append("Customer Name:" + cursor2.getString(3));
                            str.append(" " + cursor2.getString(4) + "\n");
                            str.append("Email: "+cursor2.getString(1) + "\n");
                            str.append("Phone: "+cursor2.getString(6));
                            userInfo.setText(str);
                        }
                    }
                    Cursor cursor3 = databaseHelper.viewSPData();
                    while(cursor3.moveToNext()){
                        if(cursor3.getInt(0) == spid){
                            String spServiceString = cursor3.getString(7);
                            String[] tokens2 = spServiceString.split(",");
                            for(int i = 0; i < tokens2.length; i++){
                                services.add(tokens2[i]);
                            }

                        }
                    }
                }
            }
        }
        else{
            Toast.makeText(EditServiceActivity.this, "nothing to read", Toast.LENGTH_LONG).show();
        }
        RecyclerView recyclerView = findViewById(R.id.editServiceRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomerAdapterEditService(this,services, selectedServices,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}