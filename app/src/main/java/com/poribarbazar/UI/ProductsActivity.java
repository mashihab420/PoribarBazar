package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import com.poribarbazar.Adapter.AdapterFlashSell;
import com.poribarbazar.Adapter.Adapter_item_category;
import com.poribarbazar.R;

import com.poribarbazar.databinding.ActivityProductsBinding;
import com.poribarbazar.model.ModelFlashSell;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductsActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    ArrayList<ModelFlashSell> products;
    ActivityProductsBinding binding;
    AdapterFlashSell adapterFlashSell;
    ModelFlashSell modelFlashSell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        products=new ArrayList<>();

         modelFlashSell=new ModelFlashSell();
        modelFlashSell.setCategory(getIntent().getStringExtra("category"));

        binding.title.setText(getIntent().getStringExtra("category"));

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Retrofit instance = ApiClient.getClient();
        apiInterface =instance.create(ApiInterface.class);

        products=new ArrayList<>();

        Display display = ProductsActivity.this.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

      /*  float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int columns = Math.round(dpWidth/300);
        GridLayoutManager  mLayoutManager = new GridLayoutManager(ProductsActivity.this,2);
        binding.recyler.setLayoutManager(mLayoutManager);*/


       binding.recyler.setLayoutManager(new GridLayoutManager(ProductsActivity.this,3,GridLayoutManager.VERTICAL,false));

        getCategoryProduct();
        adapterFlashSell = new AdapterFlashSell(products,ProductsActivity.this);

    }
    public void getCategoryProduct()
    {



        apiInterface.getCategorieProduct(modelFlashSell).enqueue(new Callback<List<ModelFlashSell>>() {
            @Override
            public void onResponse(Call<List<ModelFlashSell>> call, Response<List<ModelFlashSell>> response) {

                products.addAll(response.body());
                binding.recyler.setAdapter(adapterFlashSell);
                adapterFlashSell.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<List<ModelFlashSell>> call, Throwable t) {

            }
        });


    }
}