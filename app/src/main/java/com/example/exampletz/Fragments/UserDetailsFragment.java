package com.example.exampletz.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.exampletz.R;
import com.example.exampletz.Room.AppDatabase;
import com.example.exampletz.Room.UserHistory;
import com.example.exampletz.Utils.DateUtils;
import com.example.exampletz.ViewModel.UserDetailsViewModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserDetailsFragment extends Fragment {

    private ImageView avatarImageView;
    private TextView nameTextView;
    private TextView typeTextView;
    private TextView emailTextView;
    private TextView organizationTextView;
    private TextView followingCountTextView;
    private TextView followersCountTextView;
    private TextView accountCreatedTextView;

    private UserDetailsViewModel userDetailsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_details, container, false);

        avatarImageView = rootView.findViewById(R.id.avatar);
        nameTextView = rootView.findViewById(R.id.name);
        typeTextView = rootView.findViewById(R.id.type);
        emailTextView = rootView.findViewById(R.id.email);
        organizationTextView = rootView.findViewById(R.id.organization);
        followingCountTextView = rootView.findViewById(R.id.following_count);
        followersCountTextView = rootView.findViewById(R.id.followers_count);
        accountCreatedTextView = rootView.findViewById(R.id.created_at);

        userDetailsViewModel = new ViewModelProvider(this).get(UserDetailsViewModel.class);
        userDetailsViewModel.init(getArguments().getString("login"));

        userDetailsViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                Picasso.get().load(user.getAvatarUrl()).into(avatarImageView);
                nameTextView.setText(user.getName());
                typeTextView.setText(user.getType());
                if (user.getEmail() != null){
                    emailTextView.setText(user.getEmail());

                }
                if (user.getOrganization() != null){
                    organizationTextView.setText(user.getOrganization());
                }
                followingCountTextView.setText(String.valueOf(user.getFollowingCount()));
                followersCountTextView.setText(String.valueOf(user.getFollowersCount()));
                accountCreatedTextView.setText(formatDate(user.getCreatedAt()));
                long currentTimeMillis = System.currentTimeMillis();
                UserHistory userHistory = new UserHistory(user, currentTimeMillis);
                new Thread(() -> {
                    AppDatabase.getInstance(requireContext()).userHistoryDao().insert(userHistory);
                }).start();
            } else {
                Toast.makeText(requireContext(), "Something went wrong! Please try again", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private String formatDate(String dateString) {
        return DateUtils.formatDate(dateString);
    }

    public static UserDetailsFragment newInstance(String login) {
        Bundle args = new Bundle();
        args.putString("login", login);
        UserDetailsFragment fragment = new UserDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
