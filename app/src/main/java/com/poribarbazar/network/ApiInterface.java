package com.poribarbazar.network;



import com.poribarbazar.model.ModelProducts;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/products")
    Call<List<ModelProducts>> getCategories();

}
