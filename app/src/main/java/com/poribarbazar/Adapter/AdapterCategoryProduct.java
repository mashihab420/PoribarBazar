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
    String getPrice;


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

        String details = products.get(position).getPDescription();
        String url = products.get(position).getImageUrl();
        String url2 = products.get(position).getImage_url2();
        String url3 = products.get(position).getImage_url3();
        String hasSize = products.get(position).getHas_size();

        holder.p_name.setText(products.get(position).getPName());
        holder.offer.setText(products.get(position).getDicountPercentage()+" %");
        holder.p_price_discount.setText(products.get(position).getpPrice()+" BDT");
        holder.p_price_final.setText(products.get(position).getDiscountPrice()+" BDT");


        if (products.get(position).getDiscountPrice().equals("0"))
        {
            getPrice=products.get(position).getpPrice();
            holder.p_price_final.setVisibility(View.GONE);
            holder.cross.setVisibility(View.GONE);
            holder.offer.setVisibility(View.GONE);
        }else {
            holder.offer.setVisibility(View.VISIBLE);
            holder.cross.setVisibility(View.VISIBLE);
            holder.p_price_final.setVisibility(View.VISIBLE);
            getPrice=products.get(position).getDiscountPrice();
        }


        String BaseURL="https://api.poribarbazar.com/file_upload_api/";
        Glide.with(context)
                .load(BaseURL+""+products.get(position).getImageUrl())
                .into(holder.image);



        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (products.get(position).getDiscountPrice().equals("0"))
                {
                    getPrice=products.get(position).getpPrice();

                }else {
                    getPrice=products.get(position).getDiscountPrice();
                }

                final CartRepository repository = new CartRepository(context);

                ModelCartRoom modelCartRoom=new ModelCartRoom();
                modelCartRoom.setP_name(pname);
                modelCartRoom.setP_price(getPrice);
                modelCartRoom.setUrl(url);
                modelCartRoom.setQuantity("1");


                String pSize;
                if (hasSize.equals("No"))
                {
                    pSize="null";
                }
                else {
                    pSize="M";
                }
                modelCartRoom.setHasSize(hasSize);

                repository.insertSingleData(new ModelCartRoom(pname,getPrice,"1",url,pSize,hasSize));


                Tools.snackInfo_Listener((Activity) context, "Added to cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, CartActivity.class));
                    }
                });
            }
        });




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductInfoActivity.class);
                intent.putExtra("pname",pname);
                if (products.get(position).getDiscountPrice().equals("0"))
                {
                    intent.putExtra("price",products.get(position).getpPrice());

                }else {
                    intent.putExtra("price",products.get(position).getDiscountPrice());
                }
                intent.putExtra("details",details);
                intent.putExtra("image_url",url);
                intent.putExtra("image_url2",url2);
                intent.putExtra("image_url3",url3);
                intent.putExtra("hassize",hasSize);
                intent.putExtra("p_id",products.get(position).getId());
                intent.putExtra("size_list",products.get(position).getSize_list());
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView image,plus,minus,cross;
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
            cross=itemView.findViewById(R.id.item_category_cross);



        }
    }
}
