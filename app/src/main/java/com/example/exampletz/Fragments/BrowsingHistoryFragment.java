package com.example.exampletz.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exampletz.Adapters.UserHistoryAdapter;
import com.example.exampletz.R;
import com.example.exampletz.Room.AppDatabase;
import com.example.exampletz.ViewModel.BrowsingHistoryViewModel;

import java.util.Collections;

public class BrowsingHistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserHistoryAdapter adapter;
    private BrowsingHistoryViewModel viewModel;

    public BrowsingHistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.historyRecyclerView);
        adapter = new UserHistoryAdapter(requireContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        Button clearHistoryButton = view.findViewById(R.id.clearHistoryButton);
        clearHistoryButton.setOnClickListener(v -> viewModel.clearUserHistory());

        viewModel = new ViewModelProvider(this).get(BrowsingHistoryViewModel.class);
        viewModel.getUserHistories().observe(getViewLifecycleOwner(), userHistories -> {
            if (userHistories != null) {
                adapter.setUserHistoryList(userHistories);
            }
        });

        return view;
    }
}
