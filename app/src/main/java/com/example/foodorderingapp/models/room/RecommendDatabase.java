package com.example.foodorderingapp.models.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodorderingapp.models.Recommended;
import com.example.foodorderingapp.models.dao.RecommendDao;

@Database(entities = {Recommended.class}, version = 1)
public abstract class RecommendDatabase extends RoomDatabase {
    private static RecommendDatabase instance;

    public static RecommendDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    RecommendDatabase.class,
                    "recommend")
                    .build();
        }
        return instance;
    }

    public abstract RecommendDao RecommendDao();
}
