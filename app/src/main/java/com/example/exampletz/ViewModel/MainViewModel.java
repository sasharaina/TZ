package com.example.exampletz.ViewModel;

import static com.example.exampletz.Fragments.MainFragment.BASE_URL;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.exampletz.Interface.GitHubApi;
import com.example.exampletz.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<User>> userListLiveData;
    private GitHubApi gitHubApi;

    public MainViewModel(@NonNull Application application) {
        super(application);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubApi = retrofit.create(GitHubApi.class);
    }

    public LiveData<List<User>> getUserListLiveData() {
        if (userListLiveData == null) {
            userListLiveData = new MutableLiveData<>();
            loadData();
        }
        return userListLiveData;
    }

    public void loadData() {
        Call<List<User>> call = gitHubApi.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    userListLiveData.setValue(response.body());
                } else {
                    Toast.makeText(getApplication().getApplicationContext(), "Something went wrong! Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), "Something went wrong! Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
