package com.example.foodorderingapp.models.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodorderingapp.models.Popular;
import com.example.foodorderingapp.models.dao.PopularDao;

//
@Database(entities = {Popular.class}, version = 1)
public abstract class PopularDatabase extends RoomDatabase {

    private static PopularDatabase instance;

    public static PopularDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PopularDatabase.class,
                    "popular")
                    .build();
        }
        return instance;
    }

    public abstract PopularDao PopularDao();

}
