package com.example.exampletz.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.exampletz.Utils.UserConverter;

@Database(entities = {UserHistory.class}, version = 1)
@TypeConverters(UserConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserHistoryDao userHistoryDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
