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
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.poribarbazar.MainActivity;
import com.poribarbazar.MyPreferance.MysharedPreferance;
import com.poribarbazar.R;
import com.poribarbazar.model.ModelUser;
import com.poribarbazar.network.ApiClient;
import com.poribarbazar.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText phone,pass;
    ApiInterface apiInterface;
    MysharedPreferance sharedPreferance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        phone = findViewById(R.id.phoneid);
        pass = findViewById(R.id.passwordid);

        String tag = getIntent().getStringExtra("TAG");
        Toast.makeText(this, ""+tag, Toast.LENGTH_SHORT).show();


        TextView textView = findViewById(R.id.dthana);
        String text = "Don't have an account ? Create New Account";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {


                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
              //  finish();

            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }

        };
        ss.setSpan(clickableSpan1, 24,41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new BackgroundColorSpan(Color.WHITE), 24,42, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.rgb(243, 156, 38)), 24,42, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());


    }


    public void btn_login(View view) {
        String phonee = phone.getText().toString();
        String passs = pass.getText().toString();

    //input string
        String firstThreeNumber = "";     //substring containing first 4 characters

        if (phonee.length() ==11)
        {
            firstThreeNumber = phonee.substring(0, 3);
      //      Toast.makeText(this, ""+firstThreeNumber, Toast.LENGTH_SHORT).show();
            if ((firstThreeNumber.contains("017") || (firstThreeNumber.contains("019")) || (firstThreeNumber.contains("018")) || (firstThreeNumber.contains("016")) || (firstThreeNumber.contains("013")) || (firstThreeNumber.contains("015")))){


                if (passs.length() >=8){
                   // Toast.makeText(getApplicationContext(), "You CAn Login", Toast.LENGTH_SHORT).show();
                    //Here is the login api
                    login_method();
                }else {
                    pass.setError("password must be greater than 8 digit");
                    pass.requestFocus();
                    return;
                }

            }else {
                Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                phone.setError("Invalid Phone Number");
                phone.requestFocus();
                return;
            }
        }
        else
        {
            phone.setError("Phone number must be 11 digit");
            phone.requestFocus();
            return;
        }




    }

    private void login_method() {
        Retrofit instance = ApiClient.getClient();
        apiInterface = instance.create(ApiInterface.class);

        String phonee = phone.getText().toString();
        String passs = pass.getText().toString();

        ModelUser modelUser = new ModelUser();
        modelUser.setPhone(phonee);
        modelUser.setPassword(passs);
        
        apiInterface.loginUser(phonee,passs).enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
               /* if (response.body().getResponse() == "ok"){

                    sharedPreferance.setName(response.body().getName());
                    sharedPreferance.setPhone(response.body().getPhone());
                    sharedPreferance.setAddress(response.body().getAddress());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }*/
                Toast.makeText(LoginActivity.this, ""+response.body().getResponse(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}