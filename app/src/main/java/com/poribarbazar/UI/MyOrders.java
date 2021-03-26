package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.poribarbazar.Adapter.AdapterCategoryProduct;
import com.poribarbazar.Adapter.Adapter_my_orders;
import com.poribarbazar.MyPreferance.MysharedPreferance;
import com.poribarbazar.R;
import com.poribarbazar.Tools;
import com.poribarbazar.databinding.ActivityMyOrdersBinding;
import com.poribarbazar.databinding.ActivityPlaceOrderBinding;
import com.poribarbazar.model.ModelOrders;
import com.poribarbazar.model.ModelUser;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyOrders extends AppCompatActivity {


    ApiInterface apiInterface;
    Adapter_my_orders adapter_my_orders;
    ActivityMyOrdersBinding binding;
    MysharedPreferance mysharedPreferance;
    ArrayList<ModelOrders>orders=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mysharedPreferance= MysharedPreferance.getPreferences(MyOrders.this);

        binding.appBar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.appBar.title.setText("My Orders");


        binding.recyler.setLayoutManager(new LinearLayoutManager(MyOrders.this,LinearLayoutManager.VERTICAL,false));
        adapter_my_orders = new Adapter_my_orders(orders,MyOrders.this);

        getMyOrders();



    }

    public void getMyOrders()
    {
        Retrofit instance = ApiClient.getClient();
        apiInterface = instance.create(ApiInterface.class);

        ModelOrders modelOrders=new ModelOrders();
        modelOrders.setPhone(mysharedPreferance.getPhone());

        apiInterface.get_my_order(modelOrders).enqueue(new Callback<List<ModelOrders>>() {
            @Override
            public void onResponse(Call<List<ModelOrders>> call, Response<List<ModelOrders>> response) {

                binding.spinkit.setVisibility(View.GONE);
                orders.addAll(response.body());
                binding.recyler.setAdapter(adapter_my_orders);
                adapter_my_orders.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<ModelOrders>> call, Throwable t) {

                Tools.snackErr(MyOrders.this, "Failed, check the internet connection", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });

    }
}