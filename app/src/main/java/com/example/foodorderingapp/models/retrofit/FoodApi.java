package com.example.foodorderingapp.models.retrofit;

import com.example.foodorderingapp.models.FoodData;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodApi {

    @GET("fooddata.json")
    Call<List<FoodData>> getAllData();
}
