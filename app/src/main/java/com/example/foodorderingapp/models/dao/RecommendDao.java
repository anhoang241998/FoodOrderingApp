package com.example.foodorderingapp.models.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodorderingapp.models.Recommended;

import java.util.List;

@Dao
public interface RecommendDao {
    @Insert
    List<Long> insertAll(Recommended... recommendeds);

    @Query("SELECT * FROM recommended")
    List<Recommended> getAllRecommend();

    @Query("SELECT * FROM recommended WHERE uuid = :recommended")
    Recommended getRecommend(int recommended);

    @Query("DELETE FROM recommended")
    void deleteAllRecommend();
}
