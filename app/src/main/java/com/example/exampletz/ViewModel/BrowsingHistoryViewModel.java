package com.example.exampletz.ViewModel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.exampletz.App;
import com.example.exampletz.Room.AppDatabase;
import com.example.exampletz.Room.UserHistory;

import java.util.List;

public class BrowsingHistoryViewModel extends ViewModel {

    private MutableLiveData<List<UserHistory>> userHistories;
    private AppDatabase appDatabase;

    public BrowsingHistoryViewModel() {
        userHistories = new MutableLiveData<>();
        appDatabase = AppDatabase.getInstance(App.getInstance());
        loadUserHistory();
    }

    public LiveData<List<UserHistory>> getUserHistories() {
        return userHistories;
    }

    public void clearUserHistory() {
        new ClearUserHistoryTask().execute();
    }

    private void loadUserHistory() {
        appDatabase.userHistoryDao().getAll().observeForever(userHistories -> {
            if (userHistories != null) {
                this.userHistories.setValue(userHistories);
            }
        });
    }

    private class ClearUserHistoryTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            appDatabase.userHistoryDao().deleteAll();
            return null;
        }
    }
}
