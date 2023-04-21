package com.example.exampletz.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.exampletz.R;
import com.example.exampletz.Model.User;
import com.example.exampletz.Adapters.UserListAdapter;
import com.example.exampletz.ViewModel.MainViewModel;

import java.util.ArrayList;

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private UserListAdapter userListAdapter;
    private MainViewModel mainViewModel;
    public static final String BASE_URL = "https://api.github.com/";

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        listView = view.findViewById(R.id.user_list);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        userListAdapter = new UserListAdapter(getContext(), new ArrayList<>());
        listView.setAdapter(userListAdapter);
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            User user = userListAdapter.getItem(i);
            UserDetailsFragment userDetailsFragment = UserDetailsFragment.newInstance(user.getLogin());
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, userDetailsFragment)
                    .addToBackStack(null)
                    .commit();
        });

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getUserListLiveData().observe(getViewLifecycleOwner(), userList -> {
            userListAdapter.clear();
            userListAdapter.addAll(userList);
            swipeRefreshLayout.setRefreshing(false);
        });

        return view;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        mainViewModel.loadData();
    }

}



