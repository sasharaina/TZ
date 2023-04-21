package com.example.exampletz.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.exampletz.Model.User;

@Entity(tableName = "user_history")
public class UserHistory {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user")
    private User user;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    public UserHistory(User user, long timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
