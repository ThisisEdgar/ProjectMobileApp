package com.example.bearit4u;

import static java.lang.Boolean.TRUE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    DataBaseHelper databaseHelper;
    String reportContent;
    int sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Button generate = findViewById(R.id.btnUpdateReport);
        EditText report = findViewById(R.id.editTextReport);
        databaseHelper = new DataBaseHelper(this);
        ArrayList<String> services = new ArrayList<>();
        String userInfo="";
        String spInfo="";
        String serviceInfo="";
        int uid;
        int spid;

        Intent intent = getIntent();
        sid = intent.getIntExtra("SID", 0);
        int source = intent.getIntExtra("SOURCE", 0);
        Cursor cursor1 = databaseHelper.viewServiceData();

        if(cursor1.getCount()>0){
            while(cursor1.moveToNext()){
                if(cursor1.getInt(0) == sid){
                    String date = cursor1.getString(3);
                    String servicesString = cursor1.getString(4);
                    String[] tokens = servicesString.split(",");
                    for(int i = 0; i < tokens.length; i++){
                        services.add(tokens[i]);
                    }
                    uid = cursor1.getInt(2);
                    spid = cursor1.getInt(1);
                    reportContent = cursor1.getString(7);
                    int pickup = cursor1.getInt(5);
                    StringBuilder str = new StringBuilder();
                    str.append("Service ID: " + sid + "\n");
                    str.append("Date: " + date + "\n");
                    str.append("Pickup/Dropoff; ");
                    if(pickup == 0)
                        str.append("Pickup" + "\n");
                    else
                        str.append("Dropoff" + "\n");
                    str.append("Service Completed: " + "\n");
                    for(int j = 0; j < services.size(); j++){
                        str.append(j+1 + ", " + services.get(j) + "\n\n");
                    }
                    serviceInfo = String.valueOf(str);
                    Cursor cursor2 = databaseHelper.viewUserData();
                    while(cursor2.moveToNext()){
                        if(cursor2.getInt(0) == uid){
                            StringBuilder uStr = new StringBuilder();
                            uStr.append("Customer Name: " + cursor2.getString(1));
                            uStr.append(" " + cursor2.getString(2) + "\n");
                            uStr.append("Email: "+cursor2.getString(5) + "\n");
                            uStr.append("Phone: "+cursor2.getString(4) + "\n\n");
                            userInfo = String.valueOf(uStr);
                        }
                    }
                    Cursor cursor3 = databaseHelper.viewSPData();
                    while(cursor3.moveToNext()){
                        if(cursor3.getInt(0) == spid){
                            StringBuilder spStr = new StringBuilder();
                            spStr.append("Service Provider: " + cursor3.getString(2)+"\n");
                            spStr.append("Address: " + cursor3.getString(4)  +"\n");
                            spStr.append("City: " + cursor3.getString(5) +"\n");
                            spStr.append("Phone: "+cursor3.getString(6) + "\n\n");
                            spInfo = String.valueOf(spStr);
                        }
                    }
                }
            }
        }
        else{
            Toast.makeText(ReportActivity.this, "nothing to read", Toast.LENGTH_LONG).show();
        }
        if(source == 0){
            StringBuilder str = new StringBuilder();
            str.append("Service Report\n\n");
            str.append(spInfo);
            str.append(userInfo);
            str.append(serviceInfo);
            if(reportContent.equals(""))
                reportContent = String.valueOf(str);
            report.setText(reportContent);
        }
        else{
            generate.setVisibility(View.INVISIBLE);
            report.setKeyListener(null);
            if(reportContent.equals(""))
                reportContent = "No report yet, please contact your service provider.";
            report.setText(reportContent);
        }


        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportContent = report.getText().toString();
                databaseHelper.updateReport(sid, reportContent);
                try {
                    OutputStreamWriter fout =
                            new OutputStreamWriter(openFileOutput("ServiceReport"+"(SID "+sid+")"+".txt", MODE_APPEND));
                    fout.write(reportContent);
                    fout.close();
                    Toast.makeText(ReportActivity.this, "Report Generated!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}