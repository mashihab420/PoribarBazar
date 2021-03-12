package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.poribarbazar.R;
import com.poribarbazar.Tools;
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
       // binding.textView17.setText(details);

        Glide.with(getApplicationContext())
                .load(url)
                .into(binding.imageView5);



        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.snackInfo_Listener(ProductInfoActivity.this, "Added to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(ProductInfoActivity.this,CartActivity.class));
                    }
                });
            }
        });

    }
}