package com.example.bearit4u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterSpService extends RecyclerView.Adapter{
    CustomAdapterSpService.ItemClickListener itemClickListener;
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<String> users = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    LayoutInflater inflater;

    public CustomAdapterSpService(Context context, ArrayList<Integer> ids,ArrayList<String> users,ArrayList<String> dates,
                                  CustomAdapterSpService.ItemClickListener itemClickListener1){
        itemClickListener = itemClickListener1;
        this.ids = ids;
        this.users = users;
        this.dates = dates;
        inflater = LayoutInflater.from(context);
    }

    String getItem(int id){
        return users.get(id);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sp_service_recyclerview_item,parent,false);
        CustomAdapterSpService.ViewHolder viewHolder = new CustomAdapterSpService.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CustomAdapterSpService.ViewHolder)holder).txtId.setText(Integer.toString(ids.get(position)));
        ((CustomAdapterSpService.ViewHolder)holder).txtUser.setText(users.get(position));
        ((CustomAdapterSpService.ViewHolder)holder).txtDate.setText(dates.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtId;
        TextView txtUser;
        TextView txtDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtUser = itemView.findViewById(R.id.txtName);
            txtDate = itemView.findViewById(R.id.txtDate);
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
