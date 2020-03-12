package com.cheamenghuy.petproject.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cheamenghuy.petproject.R;

public class NotificationViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView_name,textView_time,textView_des;
    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.img_profile_notification);
        textView_name = itemView.findViewById(R.id.tv_name_notification);
        textView_des = itemView.findViewById(R.id.tv_des_notification);
        textView_time = itemView.findViewById(R.id.tv_time_notification);
    }
}
