package com.example.foodorderingapp.models.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodorderingapp.models.Popular;

import java.util.List;

@Dao
public interface PopularDao {
    @Insert
    List<Long> insertAll(Popular... populars);

    @Query("SELECT * FROM popular")
    List<Popular> getAllPopular();

    @Query("SELECT * FROM popular WHERE uuid = :popularID")
    Popular getPopular(int popularID);

    @Query("DELETE FROM popular")
    void deleteAllPopular();

}
