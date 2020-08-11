package com.example.foodorderingapp.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodorderingapp.models.Allmenu;
import com.example.foodorderingapp.models.FoodData;
import com.example.foodorderingapp.models.Popular;
import com.example.foodorderingapp.models.Recommended;
import com.example.foodorderingapp.models.dao.AllMenuDao;
import com.example.foodorderingapp.models.dao.PopularDao;
import com.example.foodorderingapp.models.dao.RecommendDao;
import com.example.foodorderingapp.models.retrofit.FoodApi;
import com.example.foodorderingapp.models.retrofit.FoodApiService;
import com.example.foodorderingapp.models.room.AllMenuDatabase;
import com.example.foodorderingapp.models.room.PopularDatabase;
import com.example.foodorderingapp.models.room.RecommendDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends AndroidViewModel {

    public MutableLiveData<List<Popular>> foods = new MutableLiveData<List<Popular>>();
    public MutableLiveData<List<Recommended>> recommendations = new MutableLiveData<List<Recommended>>();
    public MutableLiveData<List<Allmenu>> allMenu = new MutableLiveData<List<Allmenu>>();
    public MutableLiveData<Boolean> foodLoadError = new MutableLiveData<Boolean>();

    private FoodApi apiInterface;
    private AsyncTask<List<Popular>, Void, List<Popular>> insertPopularTask;
    private AsyncTask<List<Recommended>, Void, List<Recommended>> insertRecommendTask;
    private AsyncTask<List<Allmenu>, Void, List<Allmenu>> insertAllMenuTask;

    public ListViewModel(@NonNull Application application) {
        super(application);
    }


    public void refresh() {
        fetchFromRemote();
    }

    private void fetchFromRemote() {
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
                    insertPopularTask = new InsertPopularTask();
                    insertRecommendTask = new InsertRecommendTask();
                    insertAllMenuTask = new InsertAllmenuTask();
                    insertPopularTask.execute(foodData.get(0).getPopular());
                    insertRecommendTask.execute(foodData.get(0).getRecommended());
                    insertAllMenuTask.execute(foodData.get(0).getAllmenu());
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
    }

    private void recommendationsRetrieved(List<Recommended> recommended) {
        recommendations.setValue(recommended);
        foodLoadError.setValue(false);
    }

    private void allMenuRetrieved(List<Allmenu> allMenuList) {
        allMenu.setValue(allMenuList);
        foodLoadError.setValue(false);
    }

    private void apiErrors() {
        foodLoadError.setValue(true);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (insertPopularTask != null) {
            insertPopularTask.cancel(true);
            insertPopularTask = null;
        }

        if (insertRecommendTask != null) {
            insertRecommendTask.cancel(true);
            insertRecommendTask = null;
        }

        if (insertAllMenuTask != null) {
            insertAllMenuTask.cancel(true);
            insertAllMenuTask = null;
        }

    }

    private class InsertPopularTask extends AsyncTask<List<Popular>, Void, List<Popular>> {

        @Override
        protected List<Popular> doInBackground(List<Popular>... lists) {
            List<Popular> list = lists[0];
            PopularDao dao = PopularDatabase.getInstance(getApplication()).PopularDao();
            dao.deleteAllPopular();

            ArrayList<Popular> newList = new ArrayList<>(list);
            List<Long> result = dao.insertAll(newList.toArray(new Popular[0]));

            int i = 0;
            while (i < list.size()) {
                list.get(i).uuid = result.get(i).intValue();
                ++i;
            }

            return list;
        }

        @Override
        protected void onPostExecute(List<Popular> populars) {
            foodsRetrieved(populars);
            super.onPostExecute(populars);
        }

    }

    private class InsertRecommendTask extends AsyncTask<List<Recommended>, Void, List<Recommended>> {

        @Override
        protected List<Recommended> doInBackground(List<Recommended>... lists) {
            List<Recommended> list = lists[0];
            RecommendDao dao = RecommendDatabase.getInstance(getApplication()).RecommendDao();
            dao.deleteAllRecommend();

            ArrayList<Recommended> newList = new ArrayList<>(list);
            List<Long> result = dao.insertAll(newList.toArray(new Recommended[0]));

            int i = 0;
            while (i < list.size()) {
                list.get(i).uuid = result.get(i).intValue();
                ++i;
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<Recommended> recommendeds) {
            recommendationsRetrieved(recommendeds);
            super.onPostExecute(recommendeds);

        }
    }

    private class InsertAllmenuTask extends AsyncTask<List<Allmenu>, Void, List<Allmenu>> {

        @Override
        protected List<Allmenu> doInBackground(List<Allmenu>... lists) {
            List<Allmenu> list = lists[0];
            AllMenuDao dao = AllMenuDatabase.getInstance(getApplication()).AllMenuDao();
            dao.deleteAllMenu();

            ArrayList<Allmenu> newList = new ArrayList<>(list);
            List<Long> result = dao.insertAll(newList.toArray(new Allmenu[0]));

            int i = 0;
            while (i < list.size()) {
                list.get(i).uuid = result.get(i).intValue();
                ++i;
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<Allmenu> allmenus) {
            allMenuRetrieved(allmenus);
            super.onPostExecute(allmenus);
        }
    }

}
