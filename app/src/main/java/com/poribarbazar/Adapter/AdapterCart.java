package com.poribarbazar.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poribarbazar.model.ModelCartRoom;

import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.CartHolder> {

    ArrayList<ModelCartRoom>carts;
    Context context;

    public AdapterCart(ArrayList<ModelCartRoom> carts, Context context) {
        this.carts = carts;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterCart.CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.CartHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        public CartHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
