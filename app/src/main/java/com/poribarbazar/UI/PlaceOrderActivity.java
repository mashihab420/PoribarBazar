package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.poribarbazar.R;
import com.poribarbazar.databinding.ActivityPlaceOrderBinding;

public class PlaceOrderActivity extends AppCompatActivity {

    ActivityPlaceOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // binding = ActivityPlaceOrderBinding.inflate(getLayoutInflater())
        setContentView(R.layout.activity_place_order);

    }
}