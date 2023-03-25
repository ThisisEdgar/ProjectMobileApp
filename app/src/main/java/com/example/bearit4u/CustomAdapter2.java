package com.example.bearit4u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CustomAdapter2 extends RecyclerView.Adapter{
    ItemClickListener itemClickListener;
    ArrayList<String> names = new ArrayList<>();
    LayoutInflater inflater;

    public CustomAdapter2(Context context, ArrayList<String> names,
                          ItemClickListener itemClickListener1){
        itemClickListener = itemClickListener1;
        this.names = names;
        inflater = LayoutInflater.from(context);
    }

    String getItem(int id){
        return names.get(id);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sp_user_recyclerview_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).textView.setText(names.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
//        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            imageView = itemView.findViewById(R.id.imgSmall);
            textView = itemView.findViewById(R.id.textView);
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
