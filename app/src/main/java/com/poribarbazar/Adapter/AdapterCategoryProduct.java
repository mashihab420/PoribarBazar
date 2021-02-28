package com.poribarbazar.Adapter;

import android.content.Context;
import android.media.Image;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_products,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategoryProduct.ViewHolder holder, int position) {




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
