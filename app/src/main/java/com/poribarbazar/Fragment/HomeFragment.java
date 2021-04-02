package com.poribarbazar.Fragment;

import android.app.Activity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.poribarbazar.Adapter.AdapterCategoryProduct;
import com.poribarbazar.Adapter.AdapterFlashSell;
import com.poribarbazar.Adapter.AdapterOffers;
import com.poribarbazar.Adapter.AdapterPopularProduct;
import com.poribarbazar.Adapter.Adapter_item_category;
import com.poribarbazar.CartRepository;
import com.poribarbazar.MainActivity;
import com.poribarbazar.Tools;
import com.poribarbazar.UI.ProductsActivity;
import com.poribarbazar.databinding.ActivityProductsBinding;
import com.poribarbazar.databinding.FragmentHomeBinding;
import com.poribarbazar.model.ModelCategory;
import com.poribarbazar.model.ModelProducts;
import com.poribarbazar.model.ModelOffers;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class  HomeFragment extends Fragment {

private FragmentHomeBinding binding;
Adapter_item_category adapter_item_category;
AdapterOffers adapterOffers;
AdapterFlashSell adapterFlashSell;
AdapterPopularProduct adapterPopularProduct;

ApiInterface apiInterface;
ArrayList<ModelCategory> categories=new ArrayList<>();
ArrayList<ModelOffers> offers=new ArrayList<>();
ArrayList<ModelProducts> flashSells=new ArrayList<>();
ArrayList<ModelProducts> popularproducts=new ArrayList<>();


    ArrayList<ModelProducts> products;
    AdapterCategoryProduct adapterCategoryProduct;
    ModelProducts modelProducts=new ModelProducts();


    public HomeFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit instance = ApiClient.getClient();
        apiInterface =instance.create(ApiInterface.class);
        get_category();
        getOffers();
        getFlashSell();
        getPoputerProduct();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (container != null) {

            container.removeAllViews();

        }

        binding= FragmentHomeBinding.inflate(inflater,container,false);
        View view=binding.getRoot();


        adapter_item_category = new Adapter_item_category(categories,getActivity());
        binding.recylerCategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        adapterOffers = new AdapterOffers(offers,getActivity());
        binding.recylerOffers.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        adapterFlashSell = new AdapterFlashSell(flashSells,getActivity());
        binding.recyclerflashsell.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        adapterPopularProduct = new AdapterPopularProduct(popularproducts,getActivity());
        binding.recyclerpopularproduct.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));


        binding.editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                binding.editText.setCursorVisible(true);

                binding.searchLayout.setVisibility(View.VISIBLE);
                binding.homeLayout.setVisibility(View.GONE);
                binding.imageView4.setVisibility(View.VISIBLE);

                return false;
            }
        });


        binding.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.getText().clear();
                binding.searchLayout.setVisibility(View.GONE);
                binding.imageView4.setVisibility(View.GONE);
                binding.editText.setCursorVisible(false);
                binding.homeLayout.setVisibility(View.VISIBLE);



                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(binding.editText.getWindowToken(), 0);
            }
        });



        binding.searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Retrofit instance = ApiClient.getClient();
                apiInterface =instance.create(ApiInterface.class);
                products=new ArrayList<>();

                binding.recyclerViewSearch.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
                adapterCategoryProduct = new AdapterCategoryProduct(products,getContext());

                if (!binding.editText.getText().toString().isEmpty())
                {
                    getSearchProduct();
                }

            }
        });



        return view;
    }

    public void getSearchProduct()
    {

        binding.homeSpinkit.setVisibility(View.VISIBLE);

        modelProducts.setPName(binding.editText.getText().toString());


        apiInterface.getSearchProduct(modelProducts).enqueue(new Callback<List<ModelProducts>>() {
            @Override
            public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {
                binding.homeSpinkit.setVisibility(View.GONE);
                products.clear();
                products.addAll(response.body());
                binding.recyclerViewSearch.setAdapter(adapterCategoryProduct);
                adapterCategoryProduct.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<ModelProducts>> call, Throwable t) {
                binding.homeSpinkit.setVisibility(View.GONE);
                products.clear();
                binding.recyclerViewSearch.setAdapter(adapterCategoryProduct);
                adapterCategoryProduct.notifyDataSetChanged();

                Tools.snackErr((Activity) getContext(), "No product found !", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }
        });


    }


    public void getOffers()
    {

        apiInterface.getOffers().enqueue(new Callback<List<ModelOffers>>() {
            @Override
            public void onResponse(Call<List<ModelOffers>> call, Response<List<ModelOffers>> response) {
                offers.addAll(response.body());
                adapterOffers.notifyDataSetChanged();
                binding.recylerOffers.setAdapter(adapterOffers);

            }

            @Override
            public void onFailure(Call<List<ModelOffers>> call, Throwable t) {

            }
        });
    }

    public void getFlashSell()
    {

        apiInterface.getflashsell().enqueue(new Callback<List<ModelProducts>>() {
            @Override
            public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {
                flashSells.addAll(response.body());
                adapterFlashSell.notifyDataSetChanged();
                binding.recyclerflashsell.setAdapter(adapterFlashSell);

            }

            @Override
            public void onFailure(Call<List<ModelProducts>> call, Throwable t) {

            }
        });
    }

    public void getPoputerProduct()
    {

        apiInterface.getPopularProduct().enqueue(new Callback<List<ModelProducts>>() {
            @Override
            public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {
                popularproducts.addAll(response.body());
                adapterPopularProduct.notifyDataSetChanged();
                binding.recyclerpopularproduct.setAdapter(adapterPopularProduct);

            }

            @Override
            public void onFailure(Call<List<ModelProducts>> call, Throwable t) {

            }
        });
    }



    public void get_category(){


        apiInterface.getCategories().enqueue(new Callback<List<ModelCategory>>() {
            @Override
            public void onResponse(Call<List<ModelCategory>> call, Response<List<ModelCategory>> response) {


                categories.addAll(response.body());
                adapter_item_category.notifyDataSetChanged();
                binding.recylerCategory.setAdapter(adapter_item_category);


            }

            @Override
            public void onFailure(Call<List<ModelCategory>> call, Throwable t) {

                Toast.makeText(getContext(), "Something Wrong !", Toast.LENGTH_LONG).show();

            }
        });

/*
        Test test = new Test();
        test.setName("shihab");
        test.setPhone("01432192612");
        test.setPassword("seatssaed");

        apiInterface.createuser(test).enqueue(new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {

                try {
                    if(response.body().getMessage() !=null){
                        Toast.makeText(getContext(), "User Already exist", Toast.LENGTH_LONG).show();
                        binding.textView.setText("User Already exist");
                    }else {
                        Toast.makeText(getContext(), ""+response.body().getToken(), Toast.LENGTH_LONG).show();
                        binding.textView.setText(""+response.body().getToken());
                    }
                }catch (Exception e){
                    binding.textView.setText("User Already exist");
                    Toast.makeText(getContext(), "User Already exist", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {

            }
        });*/



    }


}