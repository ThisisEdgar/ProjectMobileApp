package com.example.bearit4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_main);

        TextView welcome = findViewById(R.id.txtWelcome);
        Button logout = findViewById(R.id.btnLogout);
        Button user = findViewById(R.id.btnUserInfo);

        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");

        welcome.setText("Welcome! " + username);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpMain.this,SpLogin.class));
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpMain.this,SPUserActivity.class));
            }
        });
    }
}