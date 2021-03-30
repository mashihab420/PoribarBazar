package com.poribarbazar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.poribarbazar.databinding.ItemMyOrderListBinding;
import com.poribarbazar.model.ModelOrders;

import java.util.ArrayList;

public class Adapter_Order_item extends RecyclerView.Adapter<Adapter_Order_item.Holder> {
    ArrayList<ModelOrders>orders;
    Context context;

    public Adapter_Order_item(ArrayList<ModelOrders>orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_Order_item.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMyOrderListBinding binding = ItemMyOrderListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Order_item.Holder holder, int position) {

        Glide.with(context)
                .load(orders.get(position).getImage())
                .into(holder.binding.image);

        holder.binding.pName.setText(orders.get(position).getP_name());
        holder.binding.price.setText(orders.get(position).getP_price());
        holder.binding.orderQuantity.setText(orders.get(position).getQuantity());
        holder.binding.size.setText(orders.get(position).getSize());



    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ItemMyOrderListBinding binding;

        public Holder(ItemMyOrderListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
