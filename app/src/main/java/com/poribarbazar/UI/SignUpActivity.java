package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.poribarbazar.R;
import com.poribarbazar.model.ModelUser;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText name, email, phone, pass, c_pass, address;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.nameid);
        phone = findViewById(R.id.phoneid);
        email = findViewById(R.id.emailid);
        address = findViewById(R.id.addressid);
        pass = findViewById(R.id.passwordid);
        c_pass = findViewById(R.id.cpassid);




    }


    private void check_data() {

        String namee = name.getText().toString();
        String phonee = phone.getText().toString();
        String emaill = email.getText().toString();
        String addresss = address.getText().toString();
        String passs = pass.getText().toString();
        String c_passs = c_pass.getText().toString();

        Retrofit instance = ApiClient.getClient();
        apiInterface = instance.create(ApiInterface.class);

        ModelUser modelUser = new ModelUser();
        modelUser.setName(namee);
        modelUser.setPhone(phonee);
        modelUser.setEmail(emaill);
        modelUser.setAddress(addresss);
        modelUser.setPassword(passs);


        apiInterface.check_data(phonee).enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                if (response.body().getResponse().equals("ok")) {

                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar mSnackBar = Snackbar.make(parentLayout, "This number is already used. Please login", Snackbar.LENGTH_LONG);
                    View view = mSnackBar.getView();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    view.setBackgroundColor(Color.RED);
                    TextView mainTextView = (TextView) (view).findViewById(R.id.snackbar_text);
                    mainTextView.setTextColor(Color.WHITE);
                    mSnackBar.show();

                    //sign_up_progress.setVisibility(View.GONE);

                    //create.setVisibility(View.VISIBLE);
                } else {

                    sendData(modelUser);

                }


            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                View parentLayout = findViewById(android.R.id.content);
                Snackbar mSnackBar = Snackbar.make(parentLayout, "Check the internet connection !", Snackbar.LENGTH_LONG);
                View view = mSnackBar.getView();
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                params.gravity = Gravity.TOP;
                view.setLayoutParams(params);
                view.setBackgroundColor(Color.RED);
                TextView mainTextView = (TextView) (view).findViewById(R.id.snackbar_text);
                mainTextView.setTextColor(Color.WHITE);
                mSnackBar.show();


                // sign_up_progress.setVisibility(View.GONE);
                // create.setVisibility(View.VISIBLE);
            }
        });


    }


    private void sendData(ModelUser modelUsers) {

        apiInterface.addUsers(modelUsers).enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                Toast.makeText(SignUpActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {


                View parentLayout = findViewById(android.R.id.content);
                Snackbar mSnackBar = Snackbar.make(parentLayout, "Check the internet connection !", Snackbar.LENGTH_LONG);
                View view = mSnackBar.getView();
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                params.gravity = Gravity.TOP;
                view.setLayoutParams(params);
                view.setBackgroundColor(Color.RED);
                TextView mainTextView = (TextView) (view).findViewById(R.id.snackbar_text);
                mainTextView.setTextColor(Color.WHITE);
                mSnackBar.show();

            }
        });

    }


    public void btn_createaccount(View view) {

       /* String namee = name.getText().toString();
        String phonee = phone.getText().toString();
        String emaill = email.getText().toString();
        String addresss = address.getText().toString();
        String passs = pass.getText().toString();
        String c_passs = c_pass.getText().toString();

        Retrofit instance = ApiClient.getClient();
        apiInterface = instance.create(ApiInterface.class);

        ModelUser modelUser = new ModelUser();
        modelUser.setName(namee);
        modelUser.setPhone(phonee);
        modelUser.setEmail(emaill);
        modelUser.setAddress(addresss);
        modelUser.setPassword(passs);


        apiInterface.user_signUp(modelUser).enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {

                Toast.makeText(SignUpActivity.this, "Create Successfull", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });*/

        check_data();
    }
}