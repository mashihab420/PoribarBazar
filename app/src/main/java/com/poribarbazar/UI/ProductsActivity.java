package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.poribarbazar.R;
import com.poribarbazar.databinding.ActivityPlaceOrderBinding;
import com.poribarbazar.databinding.ActivityProductsBinding;

public class ProductsActivity extends AppCompatActivity {

    ActivityProductsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}