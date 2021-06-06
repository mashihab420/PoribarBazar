package com.poribarbazar.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {

    private ActivityProductInfoBinding binding;

    String name,price,details,url,url2,url3,hassize,BaseURL,size,size_list,getSize;
    int quantitytext =1;
    CartRepository repository;
    Spinner spinner;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        name = intent.getStringExtra("pname");
         price = intent.getStringExtra("price");
         details = intent.getStringExtra("details");
         url = intent.getStringExtra("image_url");
         url2 = intent.getStringExtra("image_url2");
         url3 = intent.getStringExtra("image_url3");
       // String p_id = intent.getStringExtra("p_id");
         hassize = intent.getStringExtra("hassize");
         BaseURL="https://api.poribarbazar.com/file_upload_api/";


          size_list=intent.getStringExtra("size_list");

      // String s1 = "23,45,42,12,1121";

        String[] array = size_list.split(",");


        Collections.addAll(arrayList, array);

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        //Toast.makeText(this, ""+arrayList.get(3), Toast.LENGTH_SHORT).show();

       // arrayList.addAll(Arrays.asList(array));

      //  Toast.makeText(this, ""+array[0], Toast.LENGTH_SHORT).show();
    /*    for (int i=0;i<array.length;i++){
            Toast.makeText(this, ""+array[i], Toast.LENGTH_SHORT).show();
        }
*/

        binding.textView13.setText(name);

        binding.textView18.setText(price+" BDT");

        if (hassize.equals("No")){
            binding.radioGroup.setVisibility(View.GONE);
            binding.sizeCaption.setVisibility(View.GONE);
        }else {
            binding.radioGroup.setVisibility(View.VISIBLE);
            binding.sizeCaption.setVisibility(View.VISIBLE);
        }

        String quan = binding.cartQuantityId.getText().toString();
        if(quan.equals(0)){
            binding.cardnumber.setVisibility(View.GONE);
        }else {
            binding.cardnumber.setVisibility(View.VISIBLE);
        }

        ImageSlider imageSlider = findViewById(R.id.imageView5);

        List<SlideModel> slideModels = new ArrayList<>();

        if(url.equals("null") && url2.equals("null") && url3.equals("null")){

        }
        if(url2.equals("null") && url3.equals("null")){
            slideModels.add(new SlideModel(BaseURL+url));
        }else {
          /*  slideModels.clear();*/
            slideModels.add(new SlideModel(BaseURL+url));
            slideModels.add(new SlideModel(BaseURL+url2));
            slideModels.add(new SlideModel(BaseURL+url3));
        }

        // slideModels.add(new SlideModel(imgurl1,"1 Image"));


      imageSlider.setImageList(slideModels,false);

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                //Toast.makeText(ProductInfoActivity.this, "slider position: "+i, Toast.LENGTH_SHORT).show();
            }
        });


        repository = new CartRepository(this);

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                if (modelCartRooms.size()==0){
                    binding.cardnumber.setVisibility(View.GONE);
                }else {
                    binding.cardnumber.setVisibility(View.VISIBLE);
                    if(modelCartRooms.size()>99){
                        binding.cartQuantityId.setText("99+");
                    }else {
                        binding.cartQuantityId.setText(""+modelCartRooms.size());
                    }


                }
            }
        });

        binding.textView17.setText(details);


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



                if (hassize.equals("No"))
                {
                    final CartRepository repository = new CartRepository(ProductInfoActivity.this);
                    //String size = ((RadioButton)findViewById(binding.radioGroup.getCheckedRadioButtonId())).getText().toString();
                    ModelCartRoom modelCartRoom=new ModelCartRoom();
                    modelCartRoom.setP_name(name);
                    modelCartRoom.setP_price(price);
                    modelCartRoom.setUrl(url);
                    modelCartRoom.setQuantity(""+quantitytext);

                    if (hassize.equals("No"))
                    {
                       size="null";
                    }
                    else {

                        size=spinner.getSelectedItem().toString();

                    }

                    repository.insertSingleData(new ModelCartRoom(name,price,""+quantitytext,url,""+size,hassize));

                    Tools.snackInfo_Listener(ProductInfoActivity.this, "Added to Cart", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(ProductInfoActivity.this,CartActivity.class));
                        }
                    });

                }else {

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

                        if (hassize.equals("No"))
                        {
                            size="null";
                        }


                        repository.insertSingleData(new ModelCartRoom(name,price,""+quantitytext,url,""+size,hassize));

                        Tools.snackInfo_Listener(ProductInfoActivity.this, "Added to Cart", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(ProductInfoActivity.this,CartActivity.class));
                            }
                        });
                    }
                }






            }
        });

    }

    public void info_cart(View view) {
        startActivity(new Intent(ProductInfoActivity.this,CartActivity.class));
    }

    public void addToCart()
    {

    }


}