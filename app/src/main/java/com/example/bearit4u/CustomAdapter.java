package com.example.bearit4u;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    ArrayList user_id, firstName, lastName, address, phone, email, password;

    CustomAdapter(Activity activity,Context context,
                  ArrayList user_id,
                  ArrayList firstName,
                  ArrayList lastName,
                  ArrayList address,
                  ArrayList phone,
                  ArrayList email,
                  ArrayList password) {
        this.activity = activity;
        this.context=context;
        this.user_id=user_id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.phone=phone;
        this.email=email;
        this.password=password;



    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        holder.user_idtxt.setText(String.valueOf(user_id.get(position)));
        holder.firstNametxt.setText(String.valueOf(firstName.get(position)));
        holder.lastNametxt.setText(String.valueOf(lastName.get(position)));
        holder.addresstxt.setText(String.valueOf(address.get(position)));
        holder.phonetxt.setText(String.valueOf(phone.get(position)));
        holder.emailtxt.setText(String.valueOf(email.get(position)));
        holder.passwordtxt.setText(String.valueOf(password.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id.get(position)));
                intent.putExtra("name", String.valueOf(firstName.get(position)));
                intent.putExtra("lname", String.valueOf(lastName.get(position)));
                intent.putExtra("address", String.valueOf(address.get(position)));
                intent.putExtra("phone", String.valueOf(phone.get(position)));
                intent.putExtra("email", String.valueOf(email.get(position)));
                intent.putExtra("password", String.valueOf(password.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_idtxt, firstNametxt, lastNametxt, addresstxt, phonetxt, emailtxt, passwordtxt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_idtxt = itemView.findViewById(R.id.user_id_txt);
            firstNametxt = itemView.findViewById(R.id.firstNametxt);
            lastNametxt = itemView.findViewById(R.id.lastNametxt);
            addresstxt = itemView.findViewById(R.id.addresstxt);
            phonetxt= itemView.findViewById(R.id.phonetxt);
            emailtxt=itemView.findViewById(R.id.emailtxt);
            passwordtxt=itemView.findViewById(R.id.passwordtxt);
            mainLayout= itemView.findViewById(R.id.mainLayout);
        }
    }
}