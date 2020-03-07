package com.cheamenghuy.petproject.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cheamenghuy.petproject.R;

public class CareHolder extends RecyclerView.ViewHolder {
    public ImageView imageView_care;
    public TextView textView_name_care;
    public CareHolder(@NonNull View itemView) {
        super(itemView);
        imageView_care = itemView.findViewById(R.id.img_care);
        textView_name_care = itemView.findViewById(R.id.tv_name_care);
    }
}
