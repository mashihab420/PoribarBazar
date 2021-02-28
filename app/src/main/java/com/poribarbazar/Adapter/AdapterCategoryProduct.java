package com.poribarbazar.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poribarbazar.model.ModelProducts;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterCategoryProduct extends RecyclerView.Adapter<AdapterCategoryProduct.ViewHolder> {


    ArrayList<ModelProducts> Products;
    Context context;

    public AdapterCategoryProduct(ArrayList<ModelProducts> products, Context context) {
        Products = products;
        this.context = context;
    }



    @NonNull
    @Override
    public AdapterCategoryProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategoryProduct.ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return Products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
