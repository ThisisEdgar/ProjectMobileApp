package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class ViewSingleUser extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_user);

        DataBaseHelper DB = new DataBaseHelper(this);

        textView = findViewById(R.id.textViewSingleUseData);

        Cursor cursor = DB.viewSingleUserData();

        StringBuilder stringBuilder = new StringBuilder();

        while(cursor.moveToNext()){

            stringBuilder.append("\nFirst Name : " +cursor.getString(2)
            + "\nLast Name : " + cursor.getString(3));

        }

        textView.setText(stringBuilder);

    }
}