package com.example.foodorderingapp.models.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodorderingapp.models.Allmenu;
import com.example.foodorderingapp.models.dao.AllMenuDao;

@Database(entities = {Allmenu.class}, version = 1)
public abstract class AllMenuDatabase extends RoomDatabase {

    private static AllMenuDatabase instance;

    public static AllMenuDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AllMenuDatabase.class,
                    "allmenu")
                    .build();
        }
        return instance;
    }

    public abstract AllMenuDao AllMenuDao();
}
