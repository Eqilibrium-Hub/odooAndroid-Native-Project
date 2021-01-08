package com.example.odoopintegration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterProduit extends RecyclerView.Adapter<CustomAdapterProduit.MyViewHolder> {
    ArrayList<Produit> data;
    public  CustomAdapterProduit(ArrayList<Produit> data)
    {this.data=data;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new
                MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.
                layout.ligneproduit,parent,false),this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterProduit.MyViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView list_price;
        TextView id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public MyViewHolder(@NonNull View itemView,CustomAdapterProduit adapter) {
            super(itemView);
            name=itemView.findViewById(R.id.name_product);
            id=itemView.findViewById(R.id.id_product);
            list_price=itemView.findViewById(R.id.list_price_product);
        }



        public void bind(final Produit item)
        {
            id.setText("Id :"+item.getId());
            name.setText("Name :"+item.getName());
            list_price.setText("price :"+item.getList_price());
        }
    }
}
