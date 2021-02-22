package com.poribarbazar.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.poribarbazar.Adapter.Adapter_item_category;
import com.poribarbazar.databinding.FragmentHomeBinding;
import com.poribarbazar.model.ModelProducts;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static java.security.AccessController.getContext;


public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;
Adapter_item_category adapter_item_category;
ApiInterface apiInterface;
ArrayList<ModelProducts>products;

    public HomeFragment() {

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentHomeBinding.inflate(inflater,container,false);
        View view=binding.getRoot();

        Retrofit instance =  ApiClient.instance();
       // Retrofit instance = ApiClient.getClient();
        apiInterface =instance.create(ApiInterface.class);

        products=new ArrayList<>();

        adapter_item_category = new Adapter_item_category(products,getContext());
        binding.recylerFlashSell.setLayoutManager(new GridLayoutManager(getContext(),1));




        get_flash_sell();



        return view;
    }


    public void get_flash_sell(){


        apiInterface.getCategories().enqueue(new Callback<List<ModelProducts>>() {
            @Override
            public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {

                Toast.makeText(getContext(), "all data ase nai", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<List<ModelProducts>> call, Throwable t) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_LONG).show();
            }
        });



    }


}