package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.poribarbazar.R;
import com.poribarbazar.databinding.ActivityPlaceOrderBinding;
import com.poribarbazar.databinding.ActivityProductInfoBinding;

public class ProductInfoActivity extends AppCompatActivity {

    private ActivityProductInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}