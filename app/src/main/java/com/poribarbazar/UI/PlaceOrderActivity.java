package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.poribarbazar.Adapter.AdapterCart;
import com.poribarbazar.CartRepository;
import com.poribarbazar.R;
import com.poribarbazar.databinding.ActivityPlaceOrderBinding;
import com.poribarbazar.model.ModelCartRoom;
import com.poribarbazar.model.ModelOrders;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlaceOrderActivity extends AppCompatActivity {

    ActivityPlaceOrderBinding binding;
    int i = 0;
    CartRepository repository;
    AdapterCart adapterCart;
    ArrayList<ModelCartRoom> carts = new ArrayList<>();

    String price;
    String subtotal;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        price = getIntent().getStringExtra("totall");
         subtotal = getIntent().getStringExtra("subtotal");
         phone = getIntent().getStringExtra("phone");


        binding.textView9.setText(price+" BDT");
        repository = new CartRepository(getApplicationContext());

        getData();
        binding.confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderProduct();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getData() {
        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                carts.clear();
                carts.addAll(modelCartRooms);



            }
        });
    }

    private void orderProduct() {
        String payment_number = binding.paymentnumber.getText().toString();
        String trxno = binding.trxnumber.getText().toString();

        if (payment_number.equals("")){
            binding.paymentnumber.setError("Insert Number");
        }if (trxno.equals("")){
            binding.trxnumber.setError("Insert Trx No");
        }else{

            Retrofit instance = ApiClient.getClient();
            ApiInterface apiInterface = instance.create(ApiInterface.class);

            String getInvoiveID = getIntent().getStringExtra("invoiceid");
            String datetime = getIntent().getStringExtra("timedate");

            for (i = 0; i < carts.size(); i++) {

                ModelOrders modelOrders = new ModelOrders();


                modelOrders.setP_name(carts.get(i).getP_name());
                modelOrders.setP_price(carts.get(i).getP_price());
                modelOrders.setQuantity(carts.get(i).getQuantity());
                modelOrders.setInvoice_id(getInvoiveID);
                modelOrders.setPhone(phone);
                modelOrders.setPayment_phone(""+payment_number);
                modelOrders.setTrx_id(""+trxno);
                modelOrders.setSubtotal("" + subtotal);
                modelOrders.setTotal("" + price);
                modelOrders.setOrder_time("" + datetime);
                modelOrders.setShipping_fee("50");
                modelOrders.setPay_method("Bkash");


                apiInterface.insert_order(modelOrders).enqueue(new Callback<ModelOrders>() {
                    @Override
                    public void onResponse(Call<ModelOrders> call, Response<ModelOrders> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(PlaceOrderActivity.this, "order confirmed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PlaceOrderActivity.this, "Order Not Successful", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<ModelOrders> call, Throwable t) {

                        Toast.makeText(PlaceOrderActivity.this, "Check the internet connection !", Toast.LENGTH_SHORT).show();


                    }
                });

                //  Toast.makeText(CartActivity.this, ""+i, Toast.LENGTH_SHORT).show();


            }
        }




    }



}