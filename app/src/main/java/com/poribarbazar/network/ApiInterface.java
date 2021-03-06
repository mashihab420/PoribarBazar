package com.poribarbazar.network;



import com.poribarbazar.model.ModelCategory;
import com.poribarbazar.model.ModelFlashSell;
import com.poribarbazar.model.ModelOffers;
import com.poribarbazar.model.ModelProducts;
import com.poribarbazar.model.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {

    @GET("get_category.php")
    Call<List<ModelCategory>> getCategories();


    @GET("get_offers.php")
    Call<List<ModelOffers>> getOffers();

    @GET("get_flash_sell.php")
    Call<List<ModelFlashSell>> getflashsell();

    @GET("get_popular_product.php")
    Call<List<ModelFlashSell>> getPopularProduct();


    @POST("users")
    Call<Test> createuser(@Body Test test);

}
