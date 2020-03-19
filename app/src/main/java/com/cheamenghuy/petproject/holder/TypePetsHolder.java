package com.cheamenghuy.petproject.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cheamenghuy.petproject.R;

public class TypePetsHolder extends RecyclerView.ViewHolder {
    public ImageView imageView_type_of_pets;
    public TextView textView_name_type_of_pets;

    public CardView cardView;
    public TypePetsHolder(@NonNull View itemView) {
        super(itemView);
        imageView_type_of_pets = itemView.findViewById(R.id.img_type_of_care);
        textView_name_type_of_pets = itemView.findViewById(R.id.tv_type_of_care);
        cardView = itemView.findViewById(R.id.card_view_type_of_pets);
    }
}
