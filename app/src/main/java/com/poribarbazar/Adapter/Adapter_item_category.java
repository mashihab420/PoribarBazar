package com.poribarbazar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poribarbazar.R;
import com.poribarbazar.model.ModelProducts;

import java.util.ArrayList;


public class Adapter_item_category extends RecyclerView.Adapter<Adapter_item_category.Holder> {

    ArrayList<ModelProducts> categories;
    Context context;

    public Adapter_item_category(ArrayList<ModelProducts> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_item_category.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_item_category.Holder holder, int position) {





    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView category_name;
        ImageView category_image;

        public Holder(@NonNull View itemView) {
            super(itemView);

            category_name=itemView.findViewById(R.id.category_name);
            category_image=itemView.findViewById(R.id.category_image);




        }
    }
}
