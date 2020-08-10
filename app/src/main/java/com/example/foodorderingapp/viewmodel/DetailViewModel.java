package com.example.foodorderingapp.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodorderingapp.models.Allmenu;
import com.example.foodorderingapp.models.Popular;
import com.example.foodorderingapp.models.Recommended;
import com.example.foodorderingapp.models.room.AllMenuDatabase;
import com.example.foodorderingapp.models.room.PopularDatabase;
import com.example.foodorderingapp.models.room.RecommendDatabase;

public class DetailViewModel extends AndroidViewModel {

    public MutableLiveData<Popular> popularLiveData = new MutableLiveData<Popular>();
    public MutableLiveData<Recommended> recommendedLiveData = new MutableLiveData<Recommended>();
    public MutableLiveData<Allmenu> allmenuLiveData = new MutableLiveData<Allmenu>();
    private RetrievePopularTask task;
    private RetrieveRecommendTask task1;
    private RetrieveAllMenuTask task2;


    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetch(int uuid) {
        task = new RetrievePopularTask();
        task.execute(uuid);
    }

    public void fetchRecommend(int uuid) {
        task1 = new RetrieveRecommendTask();
        task1.execute(uuid);
    }

    public void fetchAllMenu(int uuid) {
        task2 = new RetrieveAllMenuTask();
        task2.execute(uuid);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (task != null) {
            task.cancel(true);
            task = null;
        }

        if (task1 != null) {
            task1.cancel(true);
            task1 = null;
        }

        if (task2 != null) {
            task2.cancel(true);
            task2 = null;
        }
    }

    private class RetrievePopularTask extends AsyncTask<Integer, Void, Popular> {

        @Override
        protected Popular doInBackground(Integer... integers) {
            int uuid = integers[0];
            return PopularDatabase.getInstance(getApplication()).PopularDao().getPopular(uuid);
        }

        @Override
        protected void onPostExecute(Popular popular) {
            popularLiveData.setValue(popular);
        }
    }

    private class RetrieveRecommendTask extends AsyncTask<Integer, Void, Recommended> {

        @Override
        protected Recommended doInBackground(Integer... integers) {
            int uuid = integers[0];
            return RecommendDatabase.getInstance(getApplication()).RecommendDao().getRecommend(uuid);
        }

        @Override
        protected void onPostExecute(Recommended recommended) {
            recommendedLiveData.setValue(recommended);
        }
    }

    private class RetrieveAllMenuTask extends AsyncTask<Integer, Void, Allmenu> {

        @Override
        protected Allmenu doInBackground(Integer... integers) {
            int uuid = integers[0];
            return AllMenuDatabase.getInstance(getApplication()).AllMenuDao().getMenu(uuid);
        }

        @Override
        protected void onPostExecute(Allmenu allmenu) {
            allmenuLiveData.setValue(allmenu);
        }
    }


}
