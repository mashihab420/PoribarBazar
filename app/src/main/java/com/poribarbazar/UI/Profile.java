package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.poribarbazar.MyPreferance.MysharedPreferance;
import com.poribarbazar.R;
import com.poribarbazar.Tools;
import com.poribarbazar.databinding.ActivityProfileBinding;
import com.poribarbazar.databinding.ActivitySignUpBinding;
import com.poribarbazar.model.ModelProducts;
import com.poribarbazar.model.ModelUser;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Profile extends AppCompatActivity {

    ActivityProfileBinding binding;
    MysharedPreferance mysharedPreferance;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mysharedPreferance=MysharedPreferance.getPreferences(Profile.this);


        binding.appBar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.appBar.title.setText("Profile");

        binding.name.setText(mysharedPreferance.getName());
        binding.phone.setText(mysharedPreferance.getName());
        binding.email.setText(mysharedPreferance.getName());
        binding.address.setText(mysharedPreferance.getName());


        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                update_profile();

            }
        });


    }

    public void update_profile()
    {
        Retrofit instance = ApiClient.getClient();
        apiInterface = instance.create(ApiInterface.class);


        ModelUser modelProducts=new ModelUser();
        modelProducts.setName(binding.name.getText().toString());
        modelProducts.setEmail(binding.phone.getText().toString());
        modelProducts.setPhone(binding.email.getText().toString());
        modelProducts.setAddress(binding.address.getText().toString());
        apiInterface.update_profile(modelProducts).enqueue(new Callback<List<ModelUser>>() {
            @Override
            public void onResponse(Call<List<ModelUser>> call, Response<List<ModelUser>> response) {
                mysharedPreferance.setName(binding.name.getText().toString());
                mysharedPreferance.setEmail(binding.email.getText().toString());
                mysharedPreferance.setAddress(binding.phone.getText().toString());

            }

            @Override
            public void onFailure(Call<List<ModelUser>> call, Throwable t) {

                Tools.snackErr(Profile.this, "Failed ,check the internet connection !", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });


    }
}