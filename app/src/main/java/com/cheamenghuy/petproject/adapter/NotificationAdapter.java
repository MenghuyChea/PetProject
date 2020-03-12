package com.cheamenghuy.petproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.holder.NotificationViewHolder;
import com.cheamenghuy.petproject.model.NotificationModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

    Context context;
    ArrayList<NotificationModel> listModels;

    public NotificationAdapter(Context context, ArrayList<NotificationModel> listModels) {
        this.context = context;
        this.listModels = listModels;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,null);
        return new NotificationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.imageView.setImageResource(listModels.get(position).getImg());
        holder.textView_name.setText(listModels.get(position).getName());
        holder.textView_des.setText(listModels.get(position).getDescription());
        holder.textView_time.setText(listModels.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }
}
