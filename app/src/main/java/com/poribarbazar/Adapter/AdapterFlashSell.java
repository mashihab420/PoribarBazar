package com.poribarbazar.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.poribarbazar.R;
import com.poribarbazar.Tools;
import com.poribarbazar.UI.CartActivity;
import com.poribarbazar.UI.ProductInfoActivity;
import com.poribarbazar.model.ModelFlashSell;
import com.poribarbazar.model.ModelOffers;

import java.util.ArrayList;

public class AdapterFlashSell extends RecyclerView.Adapter<AdapterFlashSell.MyViewHolder> {
    ArrayList<ModelFlashSell> flashSells;
    Context context;

    public AdapterFlashSell(ArrayList<ModelFlashSell> flashSells, Context context) {
        this.flashSells = flashSells;
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
        String pname = flashSells.get(position).getPName();
        String price = flashSells.get(position).getPPrice();
        String details = flashSells.get(position).getPDescription();
        String url = flashSells.get(position).getImageUrl();
        holder.p_name.setText(flashSells.get(position).getPName());

        holder.price.setText(flashSells.get(position).getPPrice()+" BDT");
        holder.disount_price.setText(flashSells.get(position).getDiscountPrice()+" BDT");
        Glide.with(context)
                .load(flashSells.get(position).getImageUrl())
                .into(holder.p_img);

        holder.pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.quantity.setVisibility(View.VISIBLE);
                holder.minus.setVisibility(View.VISIBLE);
                int quantity= Integer.parseInt(holder.quantity.getText().toString());
                quantity++;
                holder.quantity.setText(""+quantity);
                Tools.snackInfo_Listener((Activity) context, "Added to cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, CartActivity.class));
                    }
                });
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity= Integer.parseInt(holder.quantity.getText().toString());
                if (quantity==0)
                {
                    holder.quantity.setVisibility(View.GONE);
                    holder.minus.setVisibility(View.GONE);
                }else {

                    quantity--;
                    holder.quantity.setText(""+quantity);
                    holder.quantity.setVisibility(View.VISIBLE);
                    holder.minus.setVisibility(View.VISIBLE);
                }
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
        return flashSells.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView p_img,minus,pluse;
        TextView p_name,price,disount_price,quantity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            p_img = itemView.findViewById(R.id.item_procut_image);
            p_name = itemView.findViewById(R.id.item_product_name);
            price = itemView.findViewById(R.id.item_product_price);
            disount_price = itemView.findViewById(R.id.item_product_discount_price);
            quantity = itemView.findViewById(R.id.item_category_quantity);
            minus = itemView.findViewById(R.id.item_product_minus);
            pluse = itemView.findViewById(R.id.item_category_plus);

        }
    }
}
