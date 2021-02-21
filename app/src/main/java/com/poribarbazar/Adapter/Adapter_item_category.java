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

    ArrayList<ModelProducts> products;
    Context context;

    public Adapter_item_category(ArrayList<ModelProducts> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_item_category.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_products,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_item_category.Holder holder, int position) {



        holder.p_name.setText(products.get(position).getName());
      /*  holder.p_normal_price.setText(products.get(position).getSellPrice());
        holder.p_discount.setText(products.get(position).getd);*/




    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView p_name,p_normal_price,p_final_price,p_quantity,p_discount;
        ImageView p_image,p_plus,p_minus,price_cross;

        public Holder(@NonNull View itemView) {
            super(itemView);

            p_name=itemView.findViewById(R.id.item_category_name);
            p_normal_price=itemView.findViewById(R.id.item_category_normal_price);
            p_final_price=itemView.findViewById(R.id.item_category_final_price);
            p_quantity=itemView.findViewById(R.id.item_category_quantity);
            p_plus=itemView.findViewById(R.id.item_category_plus);
            p_minus=itemView.findViewById(R.id.item_category_minus);
            p_discount=itemView.findViewById(R.id.item_category_discount);
            p_image=itemView.findViewById(R.id.item_category_image);
            price_cross=itemView.findViewById(R.id.item_category_cross);



        }
    }
}
