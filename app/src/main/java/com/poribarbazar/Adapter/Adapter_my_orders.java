package com.poribarbazar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poribarbazar.databinding.ItemCategoryBinding;
import com.poribarbazar.databinding.ItemMyOrdersBinding;
import com.poribarbazar.model.ModelOrders;

import java.util.ArrayList;
import java.util.List;

public class Adapter_my_orders extends RecyclerView.Adapter<Adapter_my_orders.Holder> {

    List<ModelOrders> orders;
    Context context;

    public Adapter_my_orders(List<ModelOrders> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_my_orders.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMyOrdersBinding binding = ItemMyOrdersBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_my_orders.Holder holder, int position) {

        int positions = Integer.parseInt(""+holder.getAdapterPosition());
        int res = positions+1;
        holder.binding.orderNo.setText("Order No: "+res);
        holder.binding.date.setText("Date: "+orders.get(position).getOrder_time());
        holder.binding.invoice.setText("Invoice ID: "+orders.get(position).getInvoice_id());
        holder.binding.status.setText("Status: "+orders.get(position).getOrder_status());



    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ItemMyOrdersBinding binding;

        public Holder(ItemMyOrdersBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
