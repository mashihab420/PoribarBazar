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
import com.poribarbazar.model.ModelOffers;

import java.util.ArrayList;

public class AdapterOffers extends RecyclerView.Adapter<AdapterOffers.Holder> {

    ArrayList<ModelOffers> offers;
    Context context;


    public AdapterOffers(ArrayList<ModelOffers> offers, Context context) {
        this.offers = offers;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterOffers.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_offer,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOffers.Holder holder, int position) {


        holder.name.setText(offers.get(position).getItem_name());
        holder.discount.setText("Up-to "+offers.get(position).getDiscount_value()+" % Discount");


        Glide.with(context)
                .load(offers.get(position).getImage())
                .override(300, 200)
                .into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView name,discount;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.offer_item_name);
            discount=itemView.findViewById(R.id.offer_discount_valus);
            imageView=itemView.findViewById(R.id.offer_image);


        }
    }
}