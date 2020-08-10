package com.example.foodorderingapp.models.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodorderingapp.models.Allmenu;

import java.util.List;

@Dao
public interface AllMenuDao {
    @Insert
    List<Long> insertAll(Allmenu... allmenus);

    @Query("SELECT * FROM allmenu")
    List<Allmenu> getAllMenu();

    @Query("SELECT * FROM allmenu WHERE uuid = :allmenuID")
    Allmenu getMenu(int allmenuID);

    @Query("DELETE FROM allmenu")
    void deleteAllMenu();
}
