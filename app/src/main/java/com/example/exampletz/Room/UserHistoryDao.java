package com.example.exampletz.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserHistoryDao {
    @Query("SELECT * FROM user_history ORDER BY timestamp DESC")
    LiveData<List<UserHistory>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserHistory userHistory);

    @Delete
    void delete(UserHistory userHistory);

    @Query("DELETE FROM user_history")
    void deleteAll();
}
