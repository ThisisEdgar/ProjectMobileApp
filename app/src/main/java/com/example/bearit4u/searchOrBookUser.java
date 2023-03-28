package com.example.bearit4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class searchOrBookUser extends AppCompatActivity  implements CustomAdapterSearch.ItemClickListener{
    //erase position in the future
    Integer[] position;
    Integer pos;
    String[] city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_or_book_user);

        //Intent intent = new Intent(SelectCityandDate.class,searchOrBookUser.this);
        RecyclerView recyclerView = findViewById(R.id.providerList);
        CustomAdapterSearch customAdapterSearch = new CustomAdapterSearch(this,position,city,this);
        Integer numOfCols =1;
        recyclerView.setLayoutManager(new GridLayoutManager(this,numOfCols));
        recyclerView.setAdapter(customAdapterSearch);
    }
    // Check this
    @Override
    public void onItemClick(View view, Integer pos) {
        this.pos = pos;
    }
}