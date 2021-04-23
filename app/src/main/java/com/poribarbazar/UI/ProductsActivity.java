package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.poribarbazar.Adapter.AdapterCategoryProduct;

import com.poribarbazar.CartRepository;
import com.poribarbazar.databinding.ActivityProductsBinding;
import com.poribarbazar.model.ModelCartRoom;
import com.poribarbazar.model.ModelProducts;
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
    ArrayList<ModelProducts> products;
    ActivityProductsBinding binding;
    AdapterCategoryProduct adapterCategoryProduct;
    ModelProducts modelProducts,modelOfferProducts;
    CartRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        products=new ArrayList<>();


        if (getIntent().getStringExtra("type").equals("Offer")) {

            binding.title.setText("Offer");

        }else {
            binding.title.setText(getIntent().getStringExtra("category"));
        }

        String quan = binding.cartQuantityId.getText().toString();
        if(quan.equals(0)){
            binding.cardnumber.setVisibility(View.GONE);
        }else {
            binding.cardnumber.setVisibility(View.VISIBLE);
        }


        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        repository = new CartRepository(this);

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                if (modelCartRooms.size()==0){
                    binding.cardnumber.setVisibility(View.GONE);
                }else {
                    binding.cardnumber.setVisibility(View.VISIBLE);
                    if(modelCartRooms.size()>99){
                        binding.cartQuantityId.setText("99+");
                    }else {
                        binding.cartQuantityId.setText(""+modelCartRooms.size());
                    }


                }
            }
        });


        Retrofit instance = ApiClient.getClient();
        apiInterface =instance.create(ApiInterface.class);

        products=new ArrayList<>();

 /*       Display display = ProductsActivity.this.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

      float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int columns = Math.round(dpWidth/300);
        GridLayoutManager  mLayoutManager = new GridLayoutManager(ProductsActivity.this,columns);
        binding.recyler.setLayoutManager(mLayoutManager);*/

       binding.recyler.setLayoutManager(new GridLayoutManager(ProductsActivity.this,3,GridLayoutManager.VERTICAL,false));


       if (getIntent().getStringExtra("type").equals("Offer"))
       {
           modelOfferProducts=new ModelProducts();
           modelOfferProducts.setCategory(getIntent().getStringExtra("category"));
           modelOfferProducts.setSub_category(getIntent().getStringExtra("sub_category"));
          getOfferProduct();

       }else {
           modelProducts=new ModelProducts();
           modelProducts.setCategory(getIntent().getStringExtra("category"));
           modelOfferProducts.setSub_category(getIntent().getStringExtra("sub_category"));
           getCategoryProduct();
       }

        adapterCategoryProduct = new AdapterCategoryProduct(products,ProductsActivity.this);

    }
    
    public void getCategoryProduct()
    {



        apiInterface.getCategorieProduct(modelProducts).enqueue(new Callback<List<ModelProducts>>() {
            @Override
            public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {

                products.addAll(response.body());
                binding.recyler.setAdapter(adapterCategoryProduct);
                adapterCategoryProduct.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<ModelProducts>> call, Throwable t) {

            }
        });


    }

    public void getOfferProduct()
    {

        apiInterface.getOfferProduct(modelOfferProducts).enqueue(new Callback<List<ModelProducts>>() {
            @Override
            public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {

                products.addAll(response.body());
                binding.recyler.setAdapter(adapterCategoryProduct);
                adapterCategoryProduct.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<List<ModelProducts>> call, Throwable t) {


            }
        });


    }

    public void product_cart(View view) {
        startActivity(new Intent(ProductsActivity.this,CartActivity.class));
    }
}