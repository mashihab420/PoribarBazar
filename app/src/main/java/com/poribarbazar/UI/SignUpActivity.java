package com.poribarbazar.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.poribarbazar.CartRepository;
import com.poribarbazar.MainActivity;
import com.poribarbazar.MyPreferance.MysharedPreferance;
import com.poribarbazar.R;
import com.poribarbazar.Tools;
import com.poribarbazar.databinding.ActivityPlaceOrderBinding;
import com.poribarbazar.databinding.ActivitySignUpBinding;
import com.poribarbazar.model.ModelCartRoom;
import com.poribarbazar.model.ModelOrders;
import com.poribarbazar.model.ModelUser;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {


    ActivitySignUpBinding binding;
    ApiInterface apiInterface;
    int flag = 0;
    String subtotal = "";
    String total = "";
    String getInvoiveID = "";

    MysharedPreferance sharedPreferance;

    String deliverymethod = "";
    CartRepository repository;
    ArrayList<ModelCartRoom> carts = new ArrayList<>();

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        repository = new CartRepository(getApplicationContext());
        getData();

        Retrofit instance = ApiClient.getClient();
        apiInterface = instance.create(ApiInterface.class);

        deliverymethod = getIntent().getStringExtra("method");
        getInvoiveID = getIntent().getStringExtra("invoiceid");
        subtotal = getIntent().getStringExtra("subtotal");
        total = getIntent().getStringExtra("total");

        /* Toast.makeText(this, ""+deliverymethod, Toast.LENGTH_SHORT).show();*/


        TextView textView = findViewById(R.id.dthana);
        String text = "Already have an account ? LOGIN";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {


                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                intent.putExtra("method", "" + "MainActivity");
                intent.putExtra("invoiceid", "");
                intent.putExtra("subtotal", "");
                intent.putExtra("total", "");
                startActivity(intent);
                //finish();

            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }

        };
        ss.setSpan(clickableSpan1, 26, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new BackgroundColorSpan(Color.WHITE), 26, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.rgb(243, 156, 38)), 26, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }


    public void getData() {
        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                carts.clear();
                carts.addAll(modelCartRooms);


            }
        });
    }


    private void check_data() {

        String namee = binding.nameid.getText().toString();
        String phonee = binding.phoneid.getText().toString();
        /*   String emaill = binding.emailid.getText().toString();*/
        String addresss = binding.addressid.getText().toString();
        String passs = binding.passwordid.getText().toString();
        String c_passs = binding.cpassid.getText().toString();
        String firstThreeNumber = "";
        if (TextUtils.isEmpty(namee)) {


            binding.nameid.setError("Name required");
            binding.nameid.requestFocus();
            return;
        }


        if (phonee.length() == 11) {
            firstThreeNumber = phonee.substring(0, 3);
            //      Toast.makeText(this, ""+firstThreeNumber, Toast.LENGTH_SHORT).show();
            if ((firstThreeNumber.contains("017") || (firstThreeNumber.contains("019")) || (firstThreeNumber.contains("018")) || (firstThreeNumber.contains("016")) || (firstThreeNumber.contains("013")) || (firstThreeNumber.contains("015")))) {

                // Toast.makeText(this, "U can signup now", Toast.LENGTH_SHORT).show();
                flag = 1;

            } else {

                binding.phoneid.setError("invalid number");
                binding.phoneid.requestFocus();
                return;
            }
        } else {
            binding.phoneid.setError("must be 11 digit");
            binding.phoneid.requestFocus();
            return;
        }

      /*  if (TextUtils.isEmpty(emaill)){
            binding.emailid.setError(" email required");
            binding.emailid.requestFocus();
            return;
        }*/
        if (TextUtils.isEmpty(addresss)) {
            binding.addressid.setError("Address required");
            binding.addressid.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(passs)) {
            binding.passwordid.setError("Password required");
            binding.passwordid.requestFocus();
            return;
        } else {

            if (passs.length() == 6) {


                if (passs.equals(c_passs)) {


                    if (flag == 1) {


                        binding.spinKit.setVisibility(View.VISIBLE);

                        ModelUser modelUser = new ModelUser();
                        modelUser.setName(namee);
                        modelUser.setPhone(phonee);
                        /*     modelUser.setEmail(emaill);*/
                        modelUser.setAddress(addresss);
                        modelUser.setPassword(passs);


                        apiInterface.check_data(""+phonee).enqueue(new Callback<ModelUser>() {
                            @Override
                            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                                if (response.body().getResponse().equals("ok")) {

                                    binding.spinKit.setVisibility(View.GONE);

                                    Tools.snackErr(SignUpActivity.this, "This number is used, Please Login", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                        }
                                    });

                                } else {

                                   sendData(modelUser);
                                   // Toast.makeText(SignUpActivity.this, "Id Kule gese", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ModelUser> call, Throwable t) {
                                binding.spinKit.setVisibility(View.GONE);
                                Tools.snackErr(SignUpActivity.this, "Check the internet connection !", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                }); }
                        });

                    }


                } else {
                    binding.passwordid.setError("Password Not match");
                    binding.passwordid.requestFocus();
                    return;
                }


            } else {
                binding.cpassid.setError("password must be 6 digit");
                binding.cpassid.requestFocus();
                return;
            }


        }


    }


    private void orderProduct() {

        Retrofit instance = ApiClient.getClient();
        apiInterface = instance.create(ApiInterface.class);

        /*String getInvoiveID = getIntent().getStringExtra("invoiceid");
        String subtotal = getIntent().getStringExtra("subtotal");
        String total = getIntent().getStringExtra("total");*/
        String phone = binding.phoneid.getText().toString();

        Date d = new Date();
        CharSequence datetime = DateFormat.format("d MMMM, yyyy ", d.getTime());

        for (i = 0; i < carts.size(); i++) {

            ModelOrders modelOrders = new ModelOrders();


            modelOrders.setP_name(carts.get(i).getP_name());
            modelOrders.setP_price(carts.get(i).getP_price());
            modelOrders.setQuantity(carts.get(i).getQuantity());
            modelOrders.setInvoice_id("" + getInvoiveID);
            modelOrders.setPhone("" + phone);
            modelOrders.setSubtotal("" + subtotal);
            modelOrders.setTotal("" + total);
            modelOrders.setSize(carts.get(i).getSize());
            modelOrders.setImage(carts.get(i).getUrl());
            modelOrders.setOrder_time("" + datetime);
            modelOrders.setShipping_fee("50");
            modelOrders.setPay_method("Home Delivey");
            modelOrders.setPayment_phone("null");
            modelOrders.setTrx_id("null");
            modelOrders.setHas_size(carts.get(i).getHasSize());


            apiInterface.insert_order(modelOrders).enqueue(new Callback<ModelOrders>() {
                @Override
                public void onResponse(Call<ModelOrders> call, Response<ModelOrders> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "order confirmed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Tools.snackErr(SignUpActivity.this, "Failed !", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                    }

                }

                @Override
                public void onFailure(Call<ModelOrders> call, Throwable t) {
                    binding.spinKit.setVisibility(View.GONE);
                    Tools.snackErr(SignUpActivity.this, "Check the internet connection !", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });


                }
            });

            //  Toast.makeText(CartActivity.this, ""+i, Toast.LENGTH_SHORT).show();


        }

        Intent intent = new Intent(SignUpActivity.this, InvoiceActivity.class);
        intent.putExtra("invoiceid", "" + getInvoiveID);
        intent.putExtra("method", "Online Payment");
        intent.putExtra("subtotal", "" + subtotal);
        intent.putExtra("total", "" + total);
        startActivity(intent);


    }


    private void sendData(ModelUser modelUsers) {


        apiInterface.addUsers(modelUsers).enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                //   Toast.makeText(SignUpActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                if (response.body().getMessage().equals("Succesful")) {
                    binding.spinKit.setVisibility(View.GONE);
                    if (deliverymethod.equals("MainActivity")) {
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(SignUpActivity.this, "Account Create Successful", Toast.LENGTH_SHORT).show();
                    } else if (deliverymethod.equals("bkashDelivery")) {

                   /* String getInvoiveID = getIntent().getStringExtra("invoiceid");
                    String subtotal = getIntent().getStringExtra("subtotal");
                    String total = getIntent().getStringExtra("total");*/
                        String phone = binding.phoneid.getText().toString();

                        Date d = new Date();
                        CharSequence datetime = DateFormat.format("d MMMM, yyyy ", d.getTime());

                        Intent intentw = new Intent(SignUpActivity.this, PlaceOrderActivity.class);
                        intentw.putExtra("totall", "" + total);
                        intentw.putExtra("subtotal", "" + subtotal);
                        intentw.putExtra("phone", "" + phone);
                        intentw.putExtra("invoiceid", "" + getInvoiveID);
                        intentw.putExtra("timedate", "" + datetime);
                        startActivity(intentw);
                    } else {
                        orderProduct();
                    }

                    String nam = binding.nameid.getText().toString();
                    String pho = binding.phoneid.getText().toString();
                    /*   String ema = binding.emailid.getText().toString();*/
                    String addr = binding.addressid.getText().toString();

                    sharedPreferance.setName("" + nam);
                    sharedPreferance.setAddress("" + addr);
                    /*     sharedPreferance.setEmail(""+ema);*/
                    sharedPreferance.setPhone("" + pho);

                } else {
                    binding.spinKit.setVisibility(View.GONE);
                    Tools.snackErrInfo(SignUpActivity.this, "Failed", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                binding.spinKit.setVisibility(View.GONE);
                Tools.snackErr(SignUpActivity.this, "Check the internet connection !", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }
        });

    }


    public void btn_createaccount(View view) {


        check_data();
    }
}