package com.example.exampletz.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exampletz.R;
import com.example.exampletz.Room.UserHistory;
import com.example.exampletz.Model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserHistoryAdapter extends RecyclerView.Adapter<UserHistoryAdapter.UserHistoryViewHolder> {

    private List<UserHistory> userHistoryList = new ArrayList<>();
    private Context context;

    public UserHistoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UserHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_history, parent, false);
        return new UserHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHistoryViewHolder holder, int position) {
        UserHistory userHistory = userHistoryList.get(position);
        User user = userHistory.getUser();
        Picasso.get().load(user.getAvatarUrl()).into(holder.avatarImageView);
        holder.nameTextView.setText(user.getName());
        holder.emailTextView.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userHistoryList.size();
    }

    public void setUserHistoryList(List<UserHistory> userHistories) {
        this.userHistoryList = userHistories;
        notifyDataSetChanged();
    }

    static class UserHistoryViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView nameTextView;
        TextView emailTextView;

        public UserHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatar);
            nameTextView = itemView.findViewById(R.id.name);
            emailTextView = itemView.findViewById(R.id.email);
        }
    }
}
