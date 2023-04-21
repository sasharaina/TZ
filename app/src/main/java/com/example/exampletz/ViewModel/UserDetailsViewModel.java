package com.example.exampletz.ViewModel;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.exampletz.Interface.Service;
import com.example.exampletz.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDetailsViewModel extends ViewModel {

    private MutableLiveData<User> userLiveData;
    private String login;

    public void init(String login) {
        this.login = login;
        if (userLiveData != null) {
            return;
        }
        userLiveData = new MutableLiveData<>();
        fetchUser();
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }

    private void fetchUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<User> call = service.getUser(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    userLiveData.postValue(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }
}
