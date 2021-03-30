package com.poribarbazar.network;



import com.poribarbazar.model.ModelCategory;
import com.poribarbazar.model.ModelOrders;
import com.poribarbazar.model.ModelProducts;
import com.poribarbazar.model.ModelOffers;
import com.poribarbazar.model.ModelUser;
import com.poribarbazar.model.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("get_category.php")
    Call<List<ModelCategory>> getCategories();


    @POST("get_categoryproduct.php")
    Call<List<ModelProducts>> getCategorieProduct(@Body ModelProducts modelProducts);

    @POST("get_offer_product.php")
    Call<List<ModelProducts>> getOfferProduct(@Body ModelProducts modelProducts);


    @POST("update_user_info.php")
    Call<ModelUser> update_profile(@Body ModelUser modelUser);

    @POST("get_my_orders.php")
    Call<List<ModelOrders>> get_my_order(@Body ModelOrders modelOrders);


    @POST("get_order_item.php")
    Call<List<ModelOrders>> get_my_order_list(@Body ModelOrders modelOrders);


    @GET("get_offers.php")
    Call<List<ModelOffers>> getOffers();

    @GET("get_flash_sell.php")
    Call<List<ModelProducts>> getflashsell();

    @GET("get_popular_product.php")
    Call<List<ModelProducts>> getPopularProduct();


    @POST("users")
    Call<Test> createuser(@Body Test test);

    @POST("user_registration.php")
    Call<ModelUser> user_signUp(@Body ModelUser modelUser);

    @GET("check_data_before_create.php")
    Call<ModelUser> check_data(@Query("phone") String phone );

    @POST("insert_user_info.php")
    Call<ModelUser> addUsers(@Body ModelUser modelUsers);

    @POST("insert_order.php")
    Call<ModelOrders> insert_order(@Body ModelOrders modelOrders);

    @GET("user_login.php")
    //Call<ModelUser> loginUser(@Body ModelUser modelUsers);
    Call<ModelUser> loginUser(@Query("phone") String phone, @Query("password") String password);
}
