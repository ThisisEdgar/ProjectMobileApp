package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_main extends AppCompatActivity {
    // DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        Button button1 = findViewById(R.id.btnUser_LM);
        Button button2 = findViewById(R.id.SP_LM);

        // dbh= new DataBaseHelper(this);


        //dbh.addUserData("jwbjd", "jbdfj", "fhebjbef",
          //      "ebfjebf", "jbfjbefj", "nefgkn");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login_main.this, Login_user.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login_main.this, SpLogin.class);
                startActivity(intent);
            }
        });
    }
}