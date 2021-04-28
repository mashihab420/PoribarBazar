package com.poribarbazar.Adapter;

import android.app.Activity;
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
import com.poribarbazar.CartRepository;
import com.poribarbazar.R;
import com.poribarbazar.Tools;
import com.poribarbazar.UI.CartActivity;
import com.poribarbazar.UI.ProductInfoActivity;
import com.poribarbazar.model.ModelCartRoom;
import com.poribarbazar.model.ModelProducts;

import java.util.ArrayList;

public class AdapterCategoryProduct extends RecyclerView.Adapter<AdapterCategoryProduct.ViewHolder> {


    ArrayList<ModelProducts> products;
    Context context;
    CartRepository repository;


    public AdapterCategoryProduct(ArrayList<ModelProducts> products, Context context) {
        this.products = products;
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

        String pname = products.get(position).getPName();
        String price = products.get(position).getDiscountPrice();
        String details = products.get(position).getPDescription();
        String url = products.get(position).getImageUrl();
        String url2 = products.get(position).getImage_url2();
        String url3 = products.get(position).getImage_url3();
        String hasSize = products.get(position).getHas_size();

        holder.p_name.setText(products.get(position).getPName());
        holder.offer.setText(products.get(position).getDicountPercentage()+" %");


        holder.p_price_discount.setText(products.get(position).getpPrice()+" BDT");
        holder.p_price_final.setText(products.get(position).getDiscountPrice()+" BDT");
        String BaseURL="http://app.poribarbazar.com/poribarbazar/api/file_upload_api/";
        Glide.with(context)
                .load(BaseURL+""+products.get(position).getImageUrl())
                .into(holder.image);



        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CartRepository repository = new CartRepository(context);

                ModelCartRoom modelCartRoom=new ModelCartRoom();
                modelCartRoom.setP_name(pname);
                modelCartRoom.setP_price(price);
                modelCartRoom.setUrl(url);
                modelCartRoom.setQuantity("1");

                //    modelCartRoom.setP_name(binding.quantity.getText().toString());

                String pSize;
                if (hasSize.equals("No"))
                {
                    pSize="null";
                }
                else {
                    pSize="M";
                }
                modelCartRoom.setHasSize(hasSize);

                repository.insertSingleData(new ModelCartRoom(pname,price,"1",url,pSize,hasSize));

               /* holder.quantity.setVisibility(View.VISIBLE);
                holder.minus.setVisibility(View.VISIBLE);
                int quantity= Integer.parseInt(holder.quantity.getText().toString());
                quantity++;
                holder.quantity.setText(""+quantity);*/
                Tools.snackInfo_Listener((Activity) context, "Added to cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, CartActivity.class));
                    }
                });
            }
        });

      /*  holder.minus.setOnClickListener(new View.OnClickListener() {
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
        });*/


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductInfoActivity.class);
                intent.putExtra("pname",pname);
                intent.putExtra("price",price);
                intent.putExtra("details",details);
                intent.putExtra("image_url",url);
                intent.putExtra("image_url2",url2);
                intent.putExtra("image_url3",url3);
                intent.putExtra("hassize",hasSize);
                intent.putExtra("p_id",products.get(position).getId());
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView image,plus,minus;
        TextView offer,p_name,p_price_discount,p_price_final,quantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.item_category_image);
            plus=itemView.findViewById(R.id.item_category_plus);
            minus=itemView.findViewById(R.id.item_category_minus);
            offer=itemView.findViewById(R.id.item_category_discount);
            p_name=itemView.findViewById(R.id.item_category_p_name);
            p_price_final=itemView.findViewById(R.id.item_category_final_price);
            p_price_discount=itemView.findViewById(R.id.item_category_normal_price);
            quantity=itemView.findViewById(R.id.item_category_quantity);



        }
    }
}
