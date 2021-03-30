package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.poribarbazar.Adapter.AdapterCart;
import com.poribarbazar.Adapter.Adapter_Order_item;
import com.poribarbazar.Tools;
import com.poribarbazar.databinding.ActivityOrderItemsBinding;
import com.poribarbazar.model.ModelOrders;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderItems extends AppCompatActivity {

    ArrayList<ModelOrders>products=new ArrayList<>();
    ActivityOrderItemsBinding binding;
    ApiInterface apiInterface;
    ModelOrders modelOrders=new ModelOrders();
    Adapter_Order_item adapter_order_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Retrofit instance = ApiClient.getClient();
        apiInterface = instance.create(ApiInterface.class);


        binding.appBar.title.setText("Order List");
        binding.appBar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.myOrderRecyler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter_order_item = new Adapter_Order_item(products,getApplicationContext());


        modelOrders.setInvoice_id(getIntent().getStringExtra("invoice"));
        getItem();


    }






    private void getItem()
    {

        apiInterface.get_my_order_list(modelOrders).enqueue(new Callback<List<ModelOrders>>() {
            @Override
            public void onResponse(Call<List<ModelOrders>> call, Response<List<ModelOrders>> response) {

                products.addAll(response.body());
                binding.myOrderRecyler.setAdapter(adapter_order_item);
                adapter_order_item.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<ModelOrders>> call, Throwable t) {

                Tools.snackErr(OrderItems.this, "Check the internet connection !", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }
        });

    }
}