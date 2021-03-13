package com.poribarbazar.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
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
/*    TextInputLayout err_name, err_email, err_phone, err_pass, err_c_pass, err_address;*/
    ApiInterface apiInterface;
    int flag = 0;
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
/*
        err_name = findViewById(R.id.err_name);
        err_email = findViewById(R.id.err_email);
        err_phone = findViewById(R.id.err_phone);
        err_address = findViewById(R.id.err_address);
        err_pass = findViewById(R.id.err_pass);*/


        TextView textView = findViewById(R.id.dthana);
        String text = "Already have an account ? LOGIN";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {


                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                //finish();

            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }

        };
        ss.setSpan(clickableSpan1, 26,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new BackgroundColorSpan(Color.WHITE), 26,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.rgb(243, 156, 38)), 26,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }


    private void check_data() {
        
        

        String namee = name.getText().toString();
        String phonee = phone.getText().toString();
        String emaill = email.getText().toString();
        String addresss = address.getText().toString();
        String passs = pass.getText().toString();
        String c_passs = c_pass.getText().toString();
        String firstThreeNumber = "";
        if (TextUtils.isEmpty(namee)){


            name.setError("Please enter your name");
            name.requestFocus();
            return;
        }/*if (TextUtils.isEmpty(phonee)){
            phone.setError("Please enter your phone");
            phone.requestFocus();
            return;
        }*/
           //substring containing first 4 characters

        if (phonee.length() ==11)
        {
            firstThreeNumber = phonee.substring(0, 3);
            //      Toast.makeText(this, ""+firstThreeNumber, Toast.LENGTH_SHORT).show();
            if ((firstThreeNumber.contains("017") || (firstThreeNumber.contains("019")) || (firstThreeNumber.contains("018")) || (firstThreeNumber.contains("016")) || (firstThreeNumber.contains("013")) || (firstThreeNumber.contains("015")))){

               // Toast.makeText(this, "U can signup now", Toast.LENGTH_SHORT).show();
                flag = 1;

            }else {

                phone.setError("Invalid Phone Number");
                phone.requestFocus();
                return;
            }
        }else
        {
            phone.setError("Phone number must be 11 digit");
            phone.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(emaill)){
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }if (TextUtils.isEmpty(addresss)){
            address.setError("Please enter your address");
            address.requestFocus();
            return;
        }if (TextUtils.isEmpty(passs)){
            pass.setError("Please enter your password");
            pass.requestFocus();
            return;
        }
        else{

            if (passs.length() >=8){


                if (passs.equals(c_passs)){


                    if (flag== 1){

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


                }else {
                    c_pass.setError("Password Not match");
                    c_pass.requestFocus();
                    return;
                }


            }else {
                pass.setError("password must be greater than 8 digit");
                pass.requestFocus();
                return;
            }


        }




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