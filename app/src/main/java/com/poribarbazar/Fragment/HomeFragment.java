package com.poribarbazar.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;


import com.poribarbazar.Adapter_item_category;
import com.poribarbazar.R;
import com.poribarbazar.databinding.FragmentHomeBinding;
import com.poribarbazar.model.ModelProducts;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import java.util.ArrayList;


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

        binding=FragmentHomeBinding.inflate(inflater,container,false);
        View view=binding.getRoot();

        /*if (container!=null)
        {

            container.removeAllViews();

        }*/


        products=new ArrayList<>();

        adapter_item_category = new Adapter_item_category(products,getContext());
        binding.recylerFlashSell.setLayoutManager(new GridLayoutManager(getContext(),1));

        apiInterface = ApiClient.getApiInterface();


        get_flash_sell();



        return view;
    }


    public void get_flash_sell(){



    }


}