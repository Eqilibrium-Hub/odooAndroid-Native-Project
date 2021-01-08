package com.example.odoopintegration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterClient extends RecyclerView.Adapter<CustomAdapterClient.MyViewHolder> {

    ArrayList<Client> data;
    public  CustomAdapterClient(ArrayList<Client> data)
    {this.data=data;}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new
                MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.
                layout.ligneclient,parent,false),this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterClient.MyViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView country_id;
        TextView country_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public MyViewHolder(@NonNull View itemView,CustomAdapterClient adapter) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            country_id=itemView.findViewById(R.id.country_id);
            country_name=itemView.findViewById(R.id.country_name);
        }



        public void bind(final Client item)
        {
            name.setText("Name :"+item.getName());
            country_id.setText("Country ID :"+item.getCountry_id());
            country_name.setText("Country Name :"+item.getCountry_name());
        }
    }
}
