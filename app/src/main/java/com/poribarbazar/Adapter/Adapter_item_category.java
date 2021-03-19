package com.poribarbazar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.poribarbazar.UI.ProductsActivity;
import com.poribarbazar.databinding.ItemCategoryBinding;
import com.poribarbazar.model.ModelCategory;

import java.util.ArrayList;


public class Adapter_item_category extends RecyclerView.Adapter<Adapter_item_category.Holder> {

    ArrayList<ModelCategory> categories;
    Context context;

    public Adapter_item_category(ArrayList<ModelCategory> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_item_category.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
      //return new Holder(ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
        ItemCategoryBinding binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

      return new Holder(binding);
       // return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_item_category.Holder holder, int position) {


       // holder.category_name.setText(categories.get(position).getCategory());

        holder.binding.categoryName.setText(categories.get(position).getCategory());

        String getcat = categories.get(position).getCategory();

        Glide.with(context)
                .load(categories.get(position).getImage_url())
                .override(300, 200)
                .into(holder.binding.categoryImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, ProductsActivity.class);
                intent.putExtra("category",holder.binding.categoryName.getText().toString());
                context.startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ItemCategoryBinding binding;
        TextView category_name;
        ImageView category_image;

        public Holder(ItemCategoryBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
        /*public Holder(@NonNull @NonNull ItemCartBinding itemView) {
            super(itemView);

            category_name=itemView.findViewById(R.id.category_name);
            category_image=itemView.findViewById(R.id.category_image);




        }*/

    }
}
