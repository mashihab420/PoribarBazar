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
import com.poribarbazar.R;
import com.poribarbazar.UI.ProductsActivity;
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


        holder.name.setText(offers.get(position).getSub_category());
        holder.discount.setText("Up-to "+offers.get(position).getDiscount_value()+" % Discount");


        String BaseURL="http://shihab.techdevbd.com/poribarbazar/api/file_upload_api/";
        Glide.with(context)
                .load(BaseURL+""+offers.get(position).getImage())
                .override(300, 200)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(context, ProductsActivity.class);
                intent.putExtra("type","Offer");
                intent.putExtra("category",offers.get(position).getCategory());
                intent.putExtra("sub_category",offers.get(position).getSub_category());
                context.startActivity(intent);

            }
        });



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
