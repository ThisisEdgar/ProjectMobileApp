package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class enterLogin_user extends AppCompatActivity {
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> passwords = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_login_user);

        Button button1= findViewById(R.id.btnLogin_ELI);
        Button button2=findViewById(R.id.btnCancel_ELI);
        EditText editText1= findViewById(R.id.inputUser_ELI);
        EditText editText2 = findViewById(R.id.inputPassword_ELI);
        DataBaseHelper databaseHelper = new DataBaseHelper(this);
        Cursor cursor = databaseHelper.viewUserData();

        //gets column 1 and 6 from the database table users until it array is empty
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String usernameStr = cursor.getString(1); //Number of column 1
                String passwordStr = cursor.getString(6); //Number of column 6
                usernames.add(usernameStr);
                passwords.add(passwordStr);
            }
        }
//        else{
//            Toast.makeText(enterLogin_user.this, "Username or password incorrect, try again", Toast.LENGTH_LONG).show();
//        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUser = editText1.getText().toString();
                String inputPassword = editText2.getText().toString();
                //Credential verification
                if(inputPassword == "" || inputUser == "")
                {
                    Toast.makeText(enterLogin_user.this, "Username and Password are required"
                    ,Toast.LENGTH_LONG).show();
                }
                else if (usernames.contains(inputUser) && passwords.contains(inputPassword)){
                    Intent intent= new Intent(enterLogin_user.this, menu_user.class);
                    intent.putExtra("userName",inputUser);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(enterLogin_user.this, "Incorrect credentials"
                            ,Toast.LENGTH_LONG).show();
                }

            }
        });
        //Cancel button goes back but closes that activity
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(enterLogin_user.this, Login_user.class);
                startActivity(intent);
                finish();
            }
        });

    }
}