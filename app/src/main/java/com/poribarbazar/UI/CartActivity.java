package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.poribarbazar.Adapter.AdapterCart;
import com.poribarbazar.Adapter.Adapter_item_category;
import com.poribarbazar.CartRepository;
import com.poribarbazar.MyPreferance.MysharedPreferance;
import com.poribarbazar.OnDataSend;
import com.poribarbazar.R;
import com.poribarbazar.databinding.ActivityCartBinding;
import com.poribarbazar.databinding.ActivityMainBinding;
import com.poribarbazar.model.ModelCartRoom;
import com.poribarbazar.model.ModelOrders;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity implements OnDataSend {

    RadioButton radio_bkash, radio_cash_on_dv;
    RadioGroup radioGroup;
    Button confirmorder;
    MysharedPreferance sharedPreferance;
    CartRepository repository;
    AdapterCart adapterCart;
    ApiInterface apiInterface;
    ArrayList<ModelCartRoom> carts = new ArrayList<>();
    ActivityCartBinding binding;
    int intsub = 0;
    int total = 0;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // setContentView(R.layout.activity_cart);
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        String address = sharedPreferance.getAddress();
        radio_bkash = findViewById(R.id.radiobkash);
        radio_cash_on_dv = findViewById(R.id.radiocashondelivery);
        confirmorder = findViewById(R.id.button2);


        repository = new CartRepository(getApplicationContext());

        if (address.equals("none")) {

        } else {
            binding.addressid.setText(address);
        }


      /*  adapterCart = new AdapterCart(carts, getApplication(), repository, this);
        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false));

*/
        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterCart = new AdapterCart(carts, getApplicationContext(), repository, this);
        binding.recyclerView2.setAdapter(adapterCart);

        getData();


        radio_bkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(CartActivity.this, ""+radio_bkash.getText(), Toast.LENGTH_SHORT).show();
                confirmorder.setBackgroundColor(confirmorder.getContext().getResources().getColor(R.color.blue));
                confirmorder.setText("Continue");
            }
        });

        radio_cash_on_dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(CartActivity.this, ""+radio_cash_on_dv.getText(), Toast.LENGTH_SHORT).show();
                confirmorder.setBackgroundColor(confirmorder.getContext().getResources().getColor(R.color.blue));
                confirmorder.setText("Buy Now");
            }
        });

        confirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radio_bkash.isChecked() == true) {
                  /*  Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                    intent.putExtra("TAG", "CartActivity");
                    startActivity(intent);*/
                    String getInvoiceID = getOrderNumberGenerator();
                    if (address.equals("none")) {
                        Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                        intent.putExtra("method", "bkashDelivery");
                        intent.putExtra("invoiceid", ""+getInvoiceID);
                        intent.putExtra("subtotal", ""+intsub);
                        intent.putExtra("total", ""+total);
                        startActivity(intent);
                    } else {


                        Date d = new Date();
                        CharSequence datetime = DateFormat.format("d MMMM, yyyy ", d.getTime());

                        String pricee = binding.textView23.getText().toString();
                        Intent intentw = new Intent(CartActivity.this, PlaceOrderActivity.class);
                        intentw.putExtra("totall",""+total);
                        intentw.putExtra("subtotal",""+intsub);
                        intentw.putExtra("phone",""+sharedPreferance.getPhone());
                        intentw.putExtra("invoiceid",""+getInvoiceID);
                        intentw.putExtra("timedate",""+datetime);
                        startActivity(intentw);
                      //  Toast.makeText(CartActivity.this, "" + binding.textView23.getText().toString(), Toast.LENGTH_SHORT).show();

                    }

                } else if (radio_cash_on_dv.isChecked() == true) {

                    if (address.equals("none")) {
                        String getInvoiceID = getOrderNumberGenerator();

                        Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                        intent.putExtra("method", "cashOnDelivery");
                        intent.putExtra("invoiceid", ""+getInvoiceID);
                        intent.putExtra("subtotal", ""+intsub);
                        intent.putExtra("total", ""+total);
                        startActivity(intent);
                    } else {

                        orderProduct();

                    }
                }
            }
        });


        repository = new CartRepository(getApplicationContext());

/*
        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                arrayList.clear();
                arrayList.addAll(modelCartRooms);
                adapter.notifyDataSetChanged();



                //Toast.makeText(CartActivity.this, ""+arrayList.get(1).getSize(), Toast.LENGTH_SHORT).show();

                if (modelCartRooms.size() == 0){
                    constraintLayout.setVisibility(View.GONE);
                    emptyimage.setVisibility(View.VISIBLE);

                    emptyimage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            *//*Intent intent = new Intent(CartActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();*//*
                            onBackPressed();
                        }
                    });

                }else {
                    constraintLayout.setVisibility(View.VISIBLE);
                    emptyimage.setVisibility(View.GONE);
                }


            }
        });*/

    }

    private void orderProduct() {

        Retrofit instance = ApiClient.getClient();
        apiInterface = instance.create(ApiInterface.class);

        String getInvoiveID = getOrderNumberGenerator();
        Date d = new Date();
        CharSequence datetime = DateFormat.format("d MMMM, yyyy ", d.getTime());

        for (i = 0; i < carts.size(); i++) {

            ModelOrders modelOrders = new ModelOrders();


            modelOrders.setP_name(carts.get(i).getP_name());
            modelOrders.setP_price(carts.get(i).getP_price());
            modelOrders.setQuantity(carts.get(i).getQuantity());
            modelOrders.setInvoice_id(getInvoiveID);
            modelOrders.setPhone(sharedPreferance.getPhone());
            modelOrders.setSubtotal("" + intsub);
            modelOrders.setTotal("" + total);
            modelOrders.setOrder_time("" + datetime);
            modelOrders.setShipping_fee("50");
            modelOrders.setPay_method("Home Delivey");
            modelOrders.setPayment_phone("null");
            modelOrders.setTrx_id("null");

            apiInterface.insert_order(modelOrders).enqueue(new Callback<ModelOrders>() {
                @Override
                public void onResponse(Call<ModelOrders> call, Response<ModelOrders> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(CartActivity.this, "order confirmed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CartActivity.this, "Order Not Successful", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<ModelOrders> call, Throwable t) {

                    Toast.makeText(CartActivity.this, "Check the internet connection !", Toast.LENGTH_SHORT).show();


                }
            });

            //  Toast.makeText(CartActivity.this, ""+i, Toast.LENGTH_SHORT).show();


        }


    }

    public static String getOrderNumberGenerator() {
        // It will generate 8 digit random Number.
        // from 0 to 99999999
        Random rnd = new Random();
        int number = rnd.nextInt(99999999);

        // this will convert any number sequence into 6 character.
        return String.format("%08d", number);
    }

    public void getData() {
        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                carts.clear();
                carts.addAll(modelCartRooms);
                adapterCart.notifyDataSetChanged();
                //  binding.recyclerView2.setAdapter(adapterCart);

/*
            if (modelCartRooms.size() == 0){
                constraintLayout.setVisibility(View.GONE);
                emptyimage.setVisibility(View.VISIBLE);

             *//*   emptyimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            *//**//*Intent intent = new Intent(CartActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();*//**//*
                        onBackPressed();
                    }*//*
                });

            }else {
                constraintLayout.setVisibility(View.VISIBLE);
                emptyimage.setVisibility(View.GONE);
            }*/


            }
        });
    }


    @Override
    public void totalPrice(String subtotal) {
        intsub = Integer.parseInt(subtotal);
        total = intsub + 50;

        binding.subtotalprice.setText(subtotal + " BDT");
        binding.textView22.setText("50 BDT");
        binding.textView23.setText(total + " BDT");

    }
}