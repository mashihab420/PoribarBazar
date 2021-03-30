package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.poribarbazar.MainActivity;
import com.poribarbazar.R;
import com.poribarbazar.databinding.ActivityInvoiceBinding;
import com.poribarbazar.databinding.ActivitySignUpBinding;

public class InvoiceActivity extends AppCompatActivity {
    String subtotal = "";
    String total = "";
    String getInvoiveID = "";
    String method = "";

    ActivityInvoiceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInvoiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getInvoiveID = getIntent().getStringExtra("invoiceid");
        method = getIntent().getStringExtra("method");
        subtotal = getIntent().getStringExtra("subtotal");
        total = getIntent().getStringExtra("total");

        binding.textView21.setText("Invoice number #"+getInvoiveID);
        binding.textView23.setText(""+method);
        binding.subtotalid.setText(subtotal+" BDT");
        binding.totalid.setText("50 BDT");
        binding.deliveryfeeid.setText(total+" BDT");

        binding.appbar.title.setText("Invoice");

        binding.appbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InvoiceActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void go_home(View view) {
        Intent intent = new Intent(InvoiceActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(InvoiceActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}