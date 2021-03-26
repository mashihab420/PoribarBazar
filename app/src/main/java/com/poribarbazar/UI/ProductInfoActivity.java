package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.poribarbazar.CartRepository;
import com.poribarbazar.R;
import com.poribarbazar.Tools;
import com.poribarbazar.databinding.ActivityPlaceOrderBinding;
import com.poribarbazar.databinding.ActivityProductInfoBinding;
import com.poribarbazar.model.ModelCartRoom;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {

    private ActivityProductInfoBinding binding;

    int quantitytext =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String name = intent.getStringExtra("pname");
        String price = intent.getStringExtra("price");
        String details = intent.getStringExtra("details");
        String url = intent.getStringExtra("image_url");
        String url2 = intent.getStringExtra("image_url2");
        String url3 = intent.getStringExtra("image_url3");
        String p_id = intent.getStringExtra("p_id");
        binding.textView13.setText(name);
        binding.textView18.setText(price);
       // binding.textView17.setText(details);

       /* Glide.with(getApplicationContext())
                .load(url)
                .into(binding.imageView5);*/

        ImageSlider imageSlider = findViewById(R.id.imageView5);

        List<SlideModel> slideModels = new ArrayList<>();

        if(url2.equals("") && url3.equals("")){
            slideModels.add(new SlideModel(url));
        }else {
          /*  slideModels.clear();*/
            slideModels.add(new SlideModel(url));
            slideModels.add(new SlideModel(url2));
            slideModels.add(new SlideModel(url3));
        }

        // slideModels.add(new SlideModel(imgurl1,"1 Image"));


      imageSlider.setImageList(slideModels,false);

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                //Toast.makeText(ProductInfoActivity.this, "slider position: "+i, Toast.LENGTH_SHORT).show();
            }
        });


        binding.plusitemquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantitytext = quantitytext+1;
                binding.quantity.setText(""+quantitytext);
            }
        });

        binding.minusitemquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantitytext==1){

                }else{
                    quantitytext = quantitytext-1;
                    binding.quantity.setText(""+quantitytext);
                }
            }
        });



        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (binding.radioGroup.getCheckedRadioButtonId()==-1){
                    Toast.makeText(ProductInfoActivity.this, "You Must Select Dress Size ", Toast.LENGTH_SHORT).show();

                }else{

                    final CartRepository repository = new CartRepository(ProductInfoActivity.this);
                    String size = ((RadioButton)findViewById(binding.radioGroup.getCheckedRadioButtonId())).getText().toString();

                    ModelCartRoom modelCartRoom=new ModelCartRoom();
                    modelCartRoom.setP_name(name);
                    modelCartRoom.setP_price(price);
                    modelCartRoom.setUrl(url);
                    modelCartRoom.setQuantity(""+quantitytext);

                    //    modelCartRoom.setP_name(binding.quantity.getText().toString());

                    repository.insertSingleData(new ModelCartRoom(name,price,""+quantitytext,url,""+size));


                    Tools.snackInfo_Listener(ProductInfoActivity.this, "Added to Cart", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(ProductInfoActivity.this,CartActivity.class));
                        }
                    });
                }




            }
        });

    }
}