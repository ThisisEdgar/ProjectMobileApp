package com.example.bearit4u;

import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapterEditService extends RecyclerView.Adapter{
    CustomerAdapterEditService.ItemClickListener itemClickListener;
    ArrayList<String> services = new ArrayList<>();
    ArrayList<String> selectedServices = new ArrayList<>();
    LayoutInflater inflater;
    public CustomerAdapterEditService(Context context, ArrayList<String> services, ArrayList<String> selectedServices,
                                      CustomerAdapterEditService.ItemClickListener itemClickListener1){
        itemClickListener = itemClickListener1;
        this.services = services;
        this.selectedServices = selectedServices;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.edit_service_recyclerview_item,parent,false);
        CustomerAdapterEditService.ViewHolder viewHolder = new CustomerAdapterEditService.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).service.setText(services.get(position));
        if(selectedServices.contains(services.get(position)))
            ((ViewHolder)holder).service.setChecked(TRUE);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox service;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            service = itemView.findViewById(R.id.chboxService);
            service.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(service.isChecked() && !selectedServices.contains(service.getText().toString()))
                        selectedServices.add(service.getText().toString());
                    if(!service.isChecked() && selectedServices.contains(service.getText().toString()))
                        selectedServices.remove(selectedServices.indexOf(service.getText().toString()));
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClickListener!=null)
                        itemClickListener.onItemClick(view,getAdapterPosition());
                }
            });
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
