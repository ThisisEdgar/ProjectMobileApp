package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSingleUser extends AppCompatActivity {

    String user_id,name,lname,mail,address,phone,password;
    TextView tmail,taddress,tname,tlname,tphone;
    Button btnEdit, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_user);
        //get intent of user id
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");

        //Get Values from database
        DataBaseHelper DB = new DataBaseHelper(this);
        Cursor cursor = DB.viewSingleUserData(Integer.parseInt(user_id));

        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndexOrThrow("FirstName")); //Number of column 2
            lname = cursor.getString(cursor.getColumnIndexOrThrow("LastName"));
            mail = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
            address = cursor.getString(cursor.getColumnIndexOrThrow("Address"));
            phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
            password = cursor.getString(cursor.getColumnIndexOrThrow("Password"));
            //Toast.makeText(this, "Values: "+user_id+" "+name+" "+lname+" "+mail+" "+address+" "+phone+" "+password,
              //      Toast.LENGTH_LONG).show();

        }
        //Get textviews
        tname = findViewById(R.id.usernametxt_VSU);
        tlname = findViewById(R.id.lastNametxt_VSU);
        tmail =findViewById(R.id.emailtxt_VSU);
        taddress =findViewById(R.id.txtaddress_VSU);
        tphone = findViewById(R.id.phonetxt_VSU);
        //set text
        tname.setText("Name: "+name);
        tlname.setText("Last Name: "+lname);
        tmail.setText("Email: "+ mail);
        taddress.setText("Address: "+address);
        tphone.setText("Phone: "+phone);
        //set buttons
        btnEdit =findViewById(R.id.btnEdit_VSU);
        btnBack = findViewById(R.id.btnBack_VSU);
        //on click methods
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewSingleUser.this, menu_user.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewSingleUser.this, UpdateActivity.class);

                intent.putExtra("user_id",user_id);
                intent.putExtra("name",name);
                intent.putExtra("lname",lname);
                intent.putExtra("email",mail);
                intent.putExtra("phone",phone);
                intent.putExtra("password",password);
                intent.putExtra("address",address);
                startActivity(intent);
            }
        });


    }
}