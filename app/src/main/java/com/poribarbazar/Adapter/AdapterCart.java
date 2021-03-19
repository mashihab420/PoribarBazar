package com.poribarbazar.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.poribarbazar.CartRepository;
import com.poribarbazar.OnDataSend;
import com.poribarbazar.databinding.ItemCartBinding;
import com.poribarbazar.databinding.ItemCategoryBinding;
import com.poribarbazar.model.ModelCartRoom;

import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.CartHolder> {

    ArrayList<ModelCartRoom>carts;
    Context context;
    CartRepository repository;
    private OnDataSend dataSend;
    RadioButton radioButton;
    String selecttedtext;
    int total = 0;
    int taka = 0;
    int quantity;

    int disquantity;
    int distotal = 0;
    int distaka = 0;
    int offer;
    int totaldiscount = 0;

    int dispercent;
    int sendSubtotal = 0;
    String value = "";

    public AdapterCart(ArrayList<ModelCartRoom> carts, Context context,CartRepository repository,OnDataSend dataSend) {
        this.carts = carts;
        this.context = context;
        this.repository = repository;
        this.dataSend = dataSend;
    }

    @NonNull
    @Override
    public AdapterCart.CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemCartBinding cartBinding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new CartHolder(cartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.CartHolder holder, int position) {
        holder.cartBinding.cartitemname.setText(carts.get(position).getP_name());
        holder.cartBinding.cartitemprice.setText(carts.get(position).getP_price());
        holder.cartBinding.cartquantity.setText(carts.get(position).getQuantity());

        Glide.with(context)
                .load(carts.get(position).getUrl())
                .override(300, 200)
                .into(holder.cartBinding.cartimg);


        holder.cartBinding.deletecartitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  repository.deleteSingleData(cart.get(position).getId());

                try {
                    CartRepository repository = new CartRepository(context);
                    ModelCartRoom modelCartRoom = new ModelCartRoom();
                    modelCartRoom.setId(carts.get(position).getId());

                    total = 0;

                    distotal = 0;

                    repository.delete(modelCartRoom);
                    carts.clear();
                    carts.remove(carts.get(position).getId() - 1);

                    //taka =0;

                    notifyDataSetChanged();

                } catch (Exception e) {
                    // Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                }


            }
        });


        holder.cartBinding.cartminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantitys = Integer.parseInt(carts.get(position).getQuantity());

                //TODO ai minus hoya data room a update kora lagbe
                if (quantitys > 1) {
                    quantitys -= 1;

                    CartRepository repository = new CartRepository(context);
                    carts.get(position).setQuantity("" + quantitys);
                    ;
                    repository.update(carts.get(position));

                    holder.cartBinding.cartquantity.setText(carts.get(position).getQuantity());

                    taka = 0;
                    total = 0;

                    distotal = 0;
                    distaka = 0;

                }
            }
        });


        holder.cartBinding.cartplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantitys = Integer.parseInt(carts.get(position).getQuantity());
                quantitys += 1;

                //TODO ai plus hoya data room a update kora lagbe

                CartRepository repository = new CartRepository(context);
                carts.get(position).setQuantity("" + quantitys);
                repository.update(carts.get(position));
                holder.cartBinding.cartquantity.setText(carts.get(position).getQuantity());


                taka = 0;
                total = 0;


                distotal = 0;
                distaka = 0;

            }
        });

        //subtotal calculation start
        quantity = Integer.parseInt(carts.get(position).getQuantity());
        taka = Integer.parseInt(carts.get(position).getP_price());
        // Log.d("taka", ""+taka);
        sendSubtotal = getSubtotal(quantity, taka);
        //subtotal calculation end

        Log.d("taka", "" + total);

        dataSend.totalPrice("" + sendSubtotal);
    }

    public int getSubtotal(int quantity,int amount){
        int totalamount=0;


        for(int i = 0 ; i < carts.size(); i++) {
            int qun = Integer.parseInt(carts.get(i).getQuantity());
            int price = (Integer.parseInt(carts.get(i).getP_price()))*qun;




            totalamount = totalamount + price;
        }
        return totalamount;
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        ItemCartBinding cartBinding;

        public CartHolder(ItemCartBinding cartBinding){
            super(cartBinding.getRoot());
            this.cartBinding = cartBinding;
        }
    }
}
