package com.cheamenghuy.petproject.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cheamenghuy.petproject.R;

public class NewsHolder extends RecyclerView.ViewHolder {
    public ImageView img_pro,img_post;
    public TextView name_pro,desc;
    public CardView cardView;
    public NewsHolder(@NonNull View itemView) {
        super(itemView);
        this.cardView=itemView.findViewById(R.id.card_view);
        this.img_pro=itemView.findViewById(R.id.news_imgpro);
        this.img_post=itemView.findViewById(R.id.news_imgpost);
        this.desc=itemView.findViewById(R.id.news_despro);
        this.name_pro=itemView.findViewById(R.id.news_namepro);
    }
}
