package com.poribarbazar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.poribarbazar.Fragment.ProductFragment;
import com.poribarbazar.MainActivity;
import com.poribarbazar.R;
import com.poribarbazar.UI.ProductsActivity;
import com.poribarbazar.model.ModelCategory;
import com.poribarbazar.model.ModelProducts;

import java.util.ArrayList;

import static java.security.AccessController.getContext;


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
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_item_category.Holder holder, int position) {


        holder.category_name.setText(categories.get(position).getCategory());

        String getcat = categories.get(position).getCategory();

        Glide.with(context)
                .load(categories.get(position).getImage_url())
                .override(300, 200)
                .into(holder.category_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, ProductsActivity.class);
                intent.putExtra("category",holder.category_name.getText().toString());
                context.startActivity(intent);



            }
        });


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
