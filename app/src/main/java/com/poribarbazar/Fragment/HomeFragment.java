package com.poribarbazar.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.poribarbazar.Adapter.AdapterFlashSell;
import com.poribarbazar.Adapter.AdapterOffers;
import com.poribarbazar.Adapter.AdapterPopularProduct;
import com.poribarbazar.Adapter.Adapter_item_category;
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


public class HomeFragment extends Fragment {

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



        return view;
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