package com.example.exampletz.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.exampletz.R;
import com.example.exampletz.Model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public  class UserListAdapter extends ArrayAdapter<User> {
    private LayoutInflater inflater;

    public UserListAdapter(Context context, List<User> userList) {
        super(context, R.layout.list_item_user, userList);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_user, parent, false);
        }
        User user = getItem(position);
        ImageView avatarImageView = convertView.findViewById(R.id.avatar);
        Picasso.get().load(user.getAvatarUrl()).into(avatarImageView);

        TextView loginTextView = convertView.findViewById(R.id.login);
        loginTextView.setText(user.getLogin());

        TextView idTextView = convertView.findViewById(R.id.id);
        idTextView.setText(String.valueOf(user.getId()));
        return convertView;
    }
}
