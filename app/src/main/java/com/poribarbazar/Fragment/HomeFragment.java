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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.poribarbazar.Adapter.AdapterCategoryProduct;
import com.poribarbazar.Adapter.AdapterOffers;
import com.poribarbazar.Adapter.Adapter_item_category;
import com.poribarbazar.databinding.FragmentHomeBinding;
import com.poribarbazar.model.ModelCategory;
import com.poribarbazar.model.ModelOffers;
import com.poribarbazar.model.ModelProducts;
import com.poribarbazar.model.Test;
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
AdapterOffers adapterOffers;

ApiInterface apiInterface;
ArrayList<ModelCategory>categories;
ArrayList<ModelOffers>offers;

    public HomeFragment() {

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentHomeBinding.inflate(inflater,container,false);
        View view=binding.getRoot();

        Retrofit instance = ApiClient.getClient();
        apiInterface =instance.create(ApiInterface.class);

        categories=new ArrayList<>();
        adapter_item_category = new Adapter_item_category(categories,getActivity());
        binding.recylerCategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        offers=new ArrayList<>();
        adapterOffers = new AdapterOffers(offers,getActivity());
        binding.recylerOffers.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));


        get_category();

        getOffers();


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