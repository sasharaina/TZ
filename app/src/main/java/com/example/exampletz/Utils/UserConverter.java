package com.example.exampletz.Utils;

import androidx.room.TypeConverter;

import com.example.exampletz.Model.User;
import com.google.gson.Gson;

public class UserConverter {

    @TypeConverter
    public static User fromString(String value) {
        return new Gson().fromJson(value, User.class);
    }

    @TypeConverter
    public static String fromUser(User user) {
        Gson gson = new Gson();
        return gson.toJson(user);
    }
}
