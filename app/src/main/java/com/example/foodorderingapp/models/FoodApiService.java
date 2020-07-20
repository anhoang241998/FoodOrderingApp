package com.example.foodorderingapp.models;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodApiService {

    private static Retrofit retrofit;
    public static final String BASE_URL = "https://androidappsforyoutube.s3.ap-south-1.amazonaws.com/foodapp/";


    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
