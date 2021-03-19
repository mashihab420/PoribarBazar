package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.poribarbazar.Adapter.AdapterCart;
import com.poribarbazar.Adapter.Adapter_item_category;
import com.poribarbazar.CartRepository;
import com.poribarbazar.OnDataSend;
import com.poribarbazar.R;
import com.poribarbazar.databinding.ActivityCartBinding;
import com.poribarbazar.databinding.ActivityMainBinding;
import com.poribarbazar.model.ModelCartRoom;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements OnDataSend {

    RadioButton radio_bkash, radio_cash_on_dv;
    RadioGroup radioGroup;
    Button confirmorder;

    CartRepository repository;
    AdapterCart adapterCart;
    ArrayList<ModelCartRoom> carts = new ArrayList<>();
    ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // setContentView(R.layout.activity_cart);


        radio_bkash = findViewById(R.id.radiobkash);
        radio_cash_on_dv = findViewById(R.id.radiocashondelivery);
        confirmorder = findViewById(R.id.button2);

        repository = new CartRepository(getApplicationContext());


      /*  adapterCart = new AdapterCart(carts, getApplication(), repository, this);
        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false));

*/
        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapterCart = new AdapterCart(carts,getApplicationContext(),repository,this);
        binding.recyclerView2.setAdapter(adapterCart);

        getData();


        radio_bkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(CartActivity.this, ""+radio_bkash.getText(), Toast.LENGTH_SHORT).show();
                confirmorder.setBackgroundColor(confirmorder.getContext().getResources().getColor(R.color.blue));
                confirmorder.setText("Continue");
            }
        });

        radio_cash_on_dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(CartActivity.this, ""+radio_cash_on_dv.getText(), Toast.LENGTH_SHORT).show();
                confirmorder.setBackgroundColor(confirmorder.getContext().getResources().getColor(R.color.blue));
                confirmorder.setText("Buy Now");
            }
        });

        confirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radio_bkash.isChecked() == true) {
                    String cf = confirmorder.getText().toString();
                    Toast.makeText(getApplicationContext(), "" + cf, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CartActivity.this, PlaceOrderActivity.class);
                    startActivity(intent);
                } else if (radio_cash_on_dv.isChecked() == true) {
                    String cf = confirmorder.getText().toString();
                    Toast.makeText(getApplicationContext(), "" + cf, Toast.LENGTH_SHORT).show();
                }
            }
        });


       /* if (radioGroup.isSelected() == null){
            converorder.setBackgroundColor(R.color.blue);
        }*/

        //    Toast.makeText(this, ""+RadioButtonState, Toast.LENGTH_SHORT).show();

    }

    public void getData() {
        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                carts.clear();
                carts.addAll(modelCartRooms);
                adapterCart.notifyDataSetChanged();
              //  binding.recyclerView2.setAdapter(adapterCart);

/*
            if (modelCartRooms.size() == 0){
                constraintLayout.setVisibility(View.GONE);
                emptyimage.setVisibility(View.VISIBLE);

             *//*   emptyimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            *//**//*Intent intent = new Intent(CartActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();*//**//*
                        onBackPressed();
                    }*//*
                });

            }else {
                constraintLayout.setVisibility(View.VISIBLE);
                emptyimage.setVisibility(View.GONE);
            }*/


            }
        });
    }


    @Override
    public void totalPrice(String subtotal) {
       binding.subtotalprice.setText(subtotal+" BDT");
    }
}