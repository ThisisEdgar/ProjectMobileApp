package com.example.bearit4u;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ServiceCustomAdapter extends RecyclerView.Adapter<ServiceCustomAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ServiceModel> arrayList;

    public ServiceCustomAdapter(pickupOrDropOff context, ArrayList<ServiceModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        ServiceModel serviceModel  = arrayList.get(position);
        holder.textView.setText(serviceModel.getName());
        holder.checkBox.setChecked(serviceModel.isSelected());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private CheckBox checkBox;
        private ConstraintLayout rowItem;

        public ViewHolder(@NonNull View itemView){

            super(itemView);

            this.textView= itemView.findViewById(R.id.serviceTextView);
            this.checkBox= itemView.findViewById(R.id.serviceCheckBox);
            this.rowItem = itemView.findViewById(R.id.row2);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkBox.isChecked()) {
                        arrayList.get(getAdapterPosition()).setSelected(true);
                    }else{
                        arrayList.get(getAdapterPosition()).setSelected(false);
                    }
                    notifyDataSetChanged();
                    for(int i = 0; i < arrayList.size(); i++){
                        Log.d("TAG",arrayList.toString());

                    }
                }
            });
        }





    }
}
