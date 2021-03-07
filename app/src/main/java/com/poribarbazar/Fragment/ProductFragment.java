package com.poribarbazar.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poribarbazar.R;
import com.poribarbazar.model.ModelFlashSell;
import com.poribarbazar.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductFragment extends Fragment {


    ArrayList<ModelFlashSell>products;
    ApiInterface apiInterface;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        products=new ArrayList<>();





        return inflater.inflate(R.layout.fragment_product, container, false);
    }


}