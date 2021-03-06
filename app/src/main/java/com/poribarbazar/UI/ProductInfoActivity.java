package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
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

        Intent intent = getIntent();
        String name = intent.getStringExtra("pname");
        String price = intent.getStringExtra("price");
        String details = intent.getStringExtra("details");
        String url = intent.getStringExtra("image_url");

        binding.textView13.setText(name);
        binding.textView18.setText(price);
        binding.textView17.setText(details);

        Glide.with(getApplicationContext())
                .load(url)
                .into(binding.imageView5);


    }
}