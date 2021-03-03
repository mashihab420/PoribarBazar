package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.poribarbazar.R;

public class CartActivity extends AppCompatActivity {

    RadioButton radio_bkash,radio_cash_on_dv;
    RadioGroup     radioGroup;
    Button confirmorder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        radio_bkash = findViewById(R.id.radiobkash);
        radio_cash_on_dv = findViewById(R.id.radiocashondelivery);
        confirmorder = findViewById(R.id.button2);

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
                if (radio_bkash.isChecked()== true){
                    String cf = confirmorder.getText().toString();
                    Toast.makeText(getApplicationContext(), ""+cf, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CartActivity.this,PlaceOrderActivity.class);
                    startActivity(intent);
                }

                else if(radio_cash_on_dv.isChecked() == true){
                    String cf = confirmorder.getText().toString();
                    Toast.makeText(getApplicationContext(), ""+cf, Toast.LENGTH_SHORT).show();
                }
            }
        });


       /* if (radioGroup.isSelected() == null){
            converorder.setBackgroundColor(R.color.blue);
        }*/

    //    Toast.makeText(this, ""+RadioButtonState, Toast.LENGTH_SHORT).show();

    }



}