package com.example.bearit4u;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapterSearch extends RecyclerView.Adapter {
    ItemClickListener itemClickListener;
    Integer[] mData;
    String[] provider;
    LayoutInflater inflater;

    public CustomAdapterSearch(Context context,String[] provider,
                         ItemClickListener itemClickListener1){
        itemClickListener = itemClickListener1;
        inflater = LayoutInflater.from(context);
        this.provider = provider;
    }

    Integer getItem(Integer id){
        return mData[id];
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {
        View view = inflater.inflate(R.layout.search_or_book_recycle,parent,false);
        ViewHolderSearch viewHolder = new ViewHolderSearch(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderSearch)holder).textView.setText(provider[position]);
        ((ViewHolderSearch)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemClickListener!=null)
                    itemClickListener.onItemClick(view, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return  provider.length;
    }

    public class ViewHolderSearch extends RecyclerView.ViewHolder{

        TextView textView;
        public ViewHolderSearch(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txt1_recycle);
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
        void onItemClick(View view,Integer position);
    }
}
