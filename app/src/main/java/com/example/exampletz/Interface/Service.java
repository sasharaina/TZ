package com.example.exampletz.Interface;

import com.example.exampletz.Model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("{username}")
    Call<User> getUser(@Path("username") String username);
}
