package com.poribarbazar.network;



import com.poribarbazar.model.ModelProducts;
import com.poribarbazar.model.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {

    @GET("all")
    Call<List<ModelProducts>> getCategories();


    @POST("users")
    Call<Test> createuser(@Body Test test);

}
