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
import android.widget.Button;
import android.widget.CheckBox;
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
    int appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);
        TextView userInfo = findViewById(R.id.txtUserInfo);
        TextView sidText = findViewById(R.id.txtSid);
        EditText date = findViewById(R.id.editTextDate);
        RadioButton pickup = findViewById(R.id.radioPickup);
        RadioButton dropin = findViewById(R.id.radioDropIn);

        Button update = findViewById(R.id.btnUpdateService);

        Intent intent = getIntent();
        int sid = intent.getIntExtra("SID", 0);
        String spAndU = "";
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
                    appointment = cursor1.getInt(6);
                    if(cursor1.getInt(5) == 0)
                        pickup.setChecked(TRUE);
                    else
                        dropin.setChecked(TRUE);
                    Cursor cursor2 = databaseHelper.viewUserData();
                    while(cursor2.moveToNext()){
                        if(cursor2.getInt(0) == uid){
                            StringBuilder str = new StringBuilder();
                            str.append("Customer Name:" + cursor2.getString(1));
                            str.append(" " + cursor2.getString(2) + "\n");
                            str.append("Email: "+cursor2.getString(5) + "\n");
                            str.append("Phone: "+cursor2.getString(4)+ "\n");
                            spAndU = String.valueOf(str);
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
                            StringBuilder str = new StringBuilder();
                            str.append("\nSP Name:"+cursor3.getString(2));
                            str.append("\n"+"Address:\n"+cursor3.getString(4)+", "+cursor3.getString(5));
                            str.append("\nPhone: "+cursor3.getString(6));
                            spAndU += String.valueOf(str);
                            userInfo.setText(spAndU);
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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newDate = date.getText().toString();
                selectedServices = adapter.selectedServices;
                StringBuilder str = new StringBuilder();
                for (String selectedService: selectedServices){
                    if (selectedService.equals(selectedServices.get(selectedServices.size()-1)))
                        str.append(selectedService);
                    else
                        str.append(selectedService + ",");
                }
                int pickupValue;
                if(pickup.isChecked())
                    pickupValue = 0;
                else
                    pickupValue = 1;
                databaseHelper.updateService(sid, newDate, String.valueOf(str), pickupValue, appointment);

            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}