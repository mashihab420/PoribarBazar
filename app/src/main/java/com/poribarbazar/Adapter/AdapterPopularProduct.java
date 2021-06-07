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

public class AdapterPopularProduct extends RecyclerView.Adapter<AdapterPopularProduct.MyViewHolder> {
    ArrayList<ModelProducts> popularproduct;
    Context context;
    String getPrice;

    public AdapterPopularProduct(ArrayList<ModelProducts> popularproduct, Context context) {
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
        String pname = popularproduct.get(position).getPName();

        String details = popularproduct.get(position).getPDescription();
        String url = popularproduct.get(position).getImageUrl();
        String url2 = popularproduct.get(position).getImage_url2();
        String url3 = popularproduct.get(position).getImage_url3();
        holder.p_name.setText(popularproduct.get(position).getPName());
        holder.price.setText(popularproduct.get(position).getpPrice()+" BDT");
        holder.disount_price.setText(popularproduct.get(position).getDiscountPrice()+" BDT");
        String hasSize = popularproduct.get(position).getHas_size();
        String BaseURL="https://api.poribarbazar.com/file_upload_api/";

        if (popularproduct.get(position).getDiscountPrice().equals("0"))
        {
            getPrice=popularproduct.get(position).getpPrice();
            holder.disount_price.setVisibility(View.GONE);
            holder.price_cross.setVisibility(View.GONE);
        }else {
            holder.price_cross.setVisibility(View.VISIBLE);
            holder.disount_price.setVisibility(View.VISIBLE);
            getPrice=popularproduct.get(position).getDiscountPrice();
        }


        Glide.with(context)
                .load(BaseURL+""+popularproduct.get(position).getImageUrl())
                .into(holder.p_img);


        holder.pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (popularproduct.get(position).getDiscountPrice().equals("0"))
                {
                    getPrice=popularproduct.get(position).getpPrice();

                }else {
                    getPrice=popularproduct.get(position).getDiscountPrice();
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

                String  size_list= popularproduct.get(position).getSize_list();
                String[] array = size_list.split(",");


                modelCartRoom.setHasSize(hasSize);
                repository.insertSingleData(new ModelCartRoom(pname,getPrice,"1",url,""+array[0],hasSize));

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
                if (popularproduct.get(position).getDiscountPrice().equals("0"))
                {
                    intent.putExtra("price",popularproduct.get(position).getpPrice());

                }else {
                    intent.putExtra("price",popularproduct.get(position).getDiscountPrice());
                }
                intent.putExtra("details",details);
                intent.putExtra("image_url",url);
                intent.putExtra("image_url2",url2);
                intent.putExtra("image_url3",url3);
                intent.putExtra("hassize",hasSize);
                intent.putExtra("p_id",popularproduct.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularproduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView p_img,minus,pluse,price_cross;
        TextView p_name,price,disount_price,quantity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            p_img = itemView.findViewById(R.id.item_procut_image);
            p_name = itemView.findViewById(R.id.item_product_name);
            disount_price = itemView.findViewById(R.id.item_discount_price);
            price= itemView.findViewById(R.id.item_price);
            quantity = itemView.findViewById(R.id.item_category_quantity);
            minus = itemView.findViewById(R.id.item_product_minus);
            pluse = itemView.findViewById(R.id.item_category_plus);
            price_cross = itemView.findViewById(R.id.price_cross);
        }
    }
}
