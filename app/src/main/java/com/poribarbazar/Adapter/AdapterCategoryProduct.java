package com.poribarbazar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.poribarbazar.R;
import com.poribarbazar.UI.ProductInfoActivity;
import com.poribarbazar.model.ModelFlashSell;
import com.poribarbazar.model.ModelProducts;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterCategoryProduct extends RecyclerView.Adapter<AdapterCategoryProduct.ViewHolder> {


    ArrayList<ModelFlashSell> Products;
    Context context;


    public AdapterCategoryProduct(ArrayList<ModelFlashSell> products, Context context) {
        Products = products;
        this.context = context;
    }



    @NonNull
    @Override
    public AdapterCategoryProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_product,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategoryProduct.ViewHolder holder, int position) {

        String pname = Products.get(position).getPName();
        String price = Products.get(position).getPPrice();
        String details = Products.get(position).getPDescription();
        String url = Products.get(position).getImageUrl();
        holder.p_name.setText(Products.get(position).getPName());

        holder.p_price_normal.setText(Products.get(position).getPPrice()+" BDT");
        holder.p_price_final.setText(Products.get(position).getDiscountPrice()+" BDT");
        Glide.with(context)
                .load(Products.get(position).getImageUrl())
                .into(holder.image);

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.quantity.setText("01");
                holder.quantity.setVisibility(View.VISIBLE);
                holder.minus.setVisibility(View.VISIBLE);
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                holder.quantity.setVisibility(View.GONE);
                holder.minus.setVisibility(View.GONE);
                holder.quantity.setText("00");
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductInfoActivity.class);
                intent.putExtra("pname",pname);
                intent.putExtra("price",price);
                intent.putExtra("details",details);
                intent.putExtra("image_url",url);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return Products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView image,plus,minus;
        TextView offer,p_name,p_price_normal,p_price_final,quantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.item_category_image);
            plus=itemView.findViewById(R.id.item_category_plus);
            minus=itemView.findViewById(R.id.item_category_minus);
            offer=itemView.findViewById(R.id.item_category_discount);
            p_name=itemView.findViewById(R.id.item_category_p_name);
            p_price_final=itemView.findViewById(R.id.item_category_final_price);
            p_price_normal=itemView.findViewById(R.id.item_category_normal_price);
            quantity=itemView.findViewById(R.id.item_category_quantity);



        }
    }
}
