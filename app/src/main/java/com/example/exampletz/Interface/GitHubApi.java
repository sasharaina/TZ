package com.example.exampletz.Interface;

import com.example.exampletz.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubApi {
    @GET("users")
    Call<List<User>> getUsers();
}
