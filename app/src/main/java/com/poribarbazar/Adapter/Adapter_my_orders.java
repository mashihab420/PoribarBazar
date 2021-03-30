package com.poribarbazar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poribarbazar.Tools;
import com.poribarbazar.UI.MyOrders;
import com.poribarbazar.UI.OrderItems;
import com.poribarbazar.databinding.ItemCategoryBinding;
import com.poribarbazar.databinding.ItemMyOrdersBinding;
import com.poribarbazar.model.ModelOrders;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Adapter_my_orders extends RecyclerView.Adapter<Adapter_my_orders.Holder> {

    List<ModelOrders> orders;
    MyOrders context;

    public Adapter_my_orders(List<ModelOrders> orders, MyOrders context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_my_orders.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMyOrdersBinding binding = ItemMyOrdersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_my_orders.Holder holder, int position) {

        int positions = Integer.parseInt("" + holder.getAdapterPosition());
        int res = positions + 1;
        holder.binding.orderNo.setText("Order No: " + res);
        holder.binding.date.setText("Date: " + orders.get(position).getOrder_time());
        holder.binding.invoice.setText("Invoice ID: " + orders.get(position).getInvoice_id());
        holder.binding.status.setText("Status: " + orders.get(position).getOrder_status());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, OrderItems.class);
                intent.putExtra("invoice", orders.get(position).getInvoice_id());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.binding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int positionss = Integer.parseInt("" + holder.getAdapterPosition());
                int res = positions + 1;

                Retrofit instance = ApiClient.getClient();
               ApiInterface apiInterface = instance.create(ApiInterface.class);

                ModelOrders modelOrders=new ModelOrders();
                modelOrders.setInvoice_id(""+orders.get(position).getInvoice_id());

                apiInterface.delete_my_orders(modelOrders).enqueue(new Callback<ModelOrders>() {
                    @Override
                    public void onResponse(Call<ModelOrders> call, Response<ModelOrders> response) {
                        if (response.body().getMessage().equals("Succesful")){
                            Tools.snackInfo(context,"Delete Successful");
                            orders.remove(positionss);
                            notifyDataSetChanged();
                        }else {
                            Tools.snackInfo(context,"Delete failed, Try Again :(");
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelOrders> call, Throwable t) {

                    }
                });


            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ItemMyOrdersBinding binding;

        public Holder(ItemMyOrdersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
