package com.example.foodorderingapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodorderingapp.models.Allmenu;
import com.example.foodorderingapp.models.FoodApi;
import com.example.foodorderingapp.models.FoodApiService;
import com.example.foodorderingapp.models.FoodData;
import com.example.foodorderingapp.models.Popular;
import com.example.foodorderingapp.models.Recommended;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends AndroidViewModel {

    public MutableLiveData<List<Popular>> foods = new MutableLiveData<List<Popular>>();
    public MutableLiveData<List<Recommended>> recommendations = new MutableLiveData<List<Recommended>>();
    public MutableLiveData<List<Allmenu>> allMenu = new MutableLiveData<List<Allmenu>>();
    public MutableLiveData<Boolean> foodLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    private FoodApi apiInterface;

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh() {
        fetchFromRemote();
    }

    private void fetchFromRemote() {
        loading.setValue(true);

        apiInterface = FoodApiService.getRetrofitInstance().create(FoodApi.class);

        Call<List<FoodData>> call = apiInterface.getAllData();
        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {
                List<FoodData> foodData = response.body();
                if (foodData != null) {
                    foodsRetrieved(foodData.get(0).getPopular());
                    recommendationsRetrieved(foodData.get(0).getRecommended());
                    allMenuRetrieved(foodData.get(0).getAllmenu());
                }
            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                apiErrors();
            }
        });


    }

    private void foodsRetrieved(List<Popular> list) {
        foods.setValue(list);
        foodLoadError.setValue(false);
        loading.setValue(false);
    }

    private void recommendationsRetrieved(List<Recommended> recommended) {
        recommendations.setValue(recommended);
        foodLoadError.setValue(false);
        loading.setValue(false);
    }

    private void  allMenuRetrieved(List<Allmenu> allMenuList) {
        allMenu.setValue(allMenuList);
        foodLoadError.setValue(false);
        loading.setValue(false);
    }

    private void apiErrors() {
        foodLoadError.setValue(true);
        loading.setValue(false);
    }


}
