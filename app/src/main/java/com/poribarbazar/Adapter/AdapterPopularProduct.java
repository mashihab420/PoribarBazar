package com.poribarbazar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.poribarbazar.R;
import com.poribarbazar.model.ModelFlashSell;

import java.util.ArrayList;

public class AdapterPopularProduct extends RecyclerView.Adapter<AdapterPopularProduct.MyViewHolder> {
    ArrayList<ModelFlashSell> popularproduct;
    Context context;

    public AdapterPopularProduct(ArrayList<ModelFlashSell> popularproduct, Context context) {
        this.popularproduct = popularproduct;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_flashsell,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.p_name.setText(popularproduct.get(position).getPName());
        holder.price.setText(popularproduct.get(position).getPPrice()+" BDT");
        holder.disount_price.setText(popularproduct.get(position).getDiscountPrice()+" BDT");
        Glide.with(context)
                .load(popularproduct.get(position).getImageUrl())
                .into(holder.p_img);
        holder.pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.quentity.setText("01");
                holder.quentity.setVisibility(View.VISIBLE);
                holder.minus.setVisibility(View.VISIBLE);
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.quentity.setVisibility(View.GONE);
                holder.minus.setVisibility(View.GONE);
                holder.quentity.setText("00");
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularproduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView p_img,minus,pluse;
        TextView p_name,price,disount_price,quentity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            p_img = itemView.findViewById(R.id.item_procut_image);
            p_name = itemView.findViewById(R.id.item_product_name);
            price = itemView.findViewById(R.id.item_product_price);
            disount_price = itemView.findViewById(R.id.item_product_discount_price);
            quentity = itemView.findViewById(R.id.item_category_quantity);
            minus = itemView.findViewById(R.id.item_product_minus);
            pluse = itemView.findViewById(R.id.item_category_plus);
        }
    }
}
