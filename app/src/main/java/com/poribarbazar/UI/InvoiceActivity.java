package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.poribarbazar.CartRepository;
import com.poribarbazar.MainActivity;
import com.poribarbazar.R;
import com.poribarbazar.Tools;
import com.poribarbazar.databinding.ActivityInvoiceBinding;
import com.poribarbazar.databinding.ActivitySignUpBinding;
import com.poribarbazar.model.ModelCartRoom;

import java.util.ArrayList;
import java.util.List;

public class InvoiceActivity extends AppCompatActivity {
    String subtotal = "";
    String total = "";
    String getInvoiveID = "";
    String method = "";
    ActivityInvoiceBinding binding;
    CartRepository repository;
    ArrayList<ModelCartRoom> carts = new ArrayList<>();
    ModelCartRoom modelCartRoom = new ModelCartRoom();
    private String TAG;
    int size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInvoiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = new CartRepository(InvoiceActivity.this);

        getInvoiveID = getIntent().getStringExtra("invoiceid");
        method = getIntent().getStringExtra("method");
        subtotal = getIntent().getStringExtra("subtotal");
        total = getIntent().getStringExtra("total");

        binding.textView21.setText("Invoice number #" + getInvoiveID);
        binding.textView23.setText("" + method);
        binding.subtotalid.setText(subtotal + " BDT");
        binding.totalid.setText("50 BDT");
        binding.deliveryfeeid.setText(total + " BDT");
        binding.appbar.title.setText("Invoice");

        getData();


        binding.appbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InvoiceActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void go_home(View view) {
        Intent intent = new Intent(InvoiceActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(InvoiceActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void getData() {
        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {
                carts.clear();
                carts.addAll(modelCartRooms);

                for (int j = 0; j < carts.size(); j++) {
                    modelCartRoom.setId(carts.get(j).getId());
                    repository.delete(modelCartRoom);
                }

            }
        });
    }
}