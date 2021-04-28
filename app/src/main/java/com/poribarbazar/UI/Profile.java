package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.poribarbazar.MainActivity;
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
        binding.phone.setText(mysharedPreferance.getPhone());
       // binding.email.setText(mysharedPreferance.getemail());
        binding.address.setText(mysharedPreferance.getAddress());


        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.name.getText().toString().isEmpty()
                        ||binding.address.getText().toString().isEmpty()
                )
                {
                    Tools.snackErr(Profile.this, "Required filed can't be empty", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }else {
                    update_profile();
                }



            }
        });


    }

    public void update_profile()
    {
        Retrofit instance = ApiClient.getClient();
        apiInterface = instance.create(ApiInterface.class);


        ModelUser modelUser=new ModelUser();
        modelUser.setName(binding.name.getText().toString());
        modelUser.setPhone(binding.phone.getText().toString());
      //  modelUser.setEmail(binding.email.getText().toString());
        modelUser.setAddress(binding.address.getText().toString());


        apiInterface.update_profile(modelUser).enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {

                mysharedPreferance.setName(binding.name.getText().toString());
             //   mysharedPreferance.setEmail(binding.email.getText().toString());
                mysharedPreferance.setAddress(binding.address.getText().toString());
                Tools.snackOK(Profile.this, "Successful Saved", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startActivity(new Intent(Profile.this, MainActivity.class));
                    }
                });





            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {

                Tools.snackErr(Profile.this, "Failed ,check the internet connection !", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }
        });




    }
}