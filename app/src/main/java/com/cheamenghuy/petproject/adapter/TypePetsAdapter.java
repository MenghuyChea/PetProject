package com.cheamenghuy.petproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.holder.TypePetsHolder;
import com.cheamenghuy.petproject.model.TypePetsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TypePetsAdapter extends RecyclerView.Adapter<TypePetsAdapter.TypePetsHolder> {

    Context context;
    ArrayList<TypePetsModel> listModels;
    OnClickItemListener mListener;

    public interface OnClickItemListener{
        public void onItemClick(int position);

    }

    public void setOnClickItemListener(OnClickItemListener mListener) {
        this.mListener = mListener;
    }

    public TypePetsAdapter(Context context, ArrayList<TypePetsModel> listModels) {
        this.context = context;
        this.listModels = listModels;
    }

    @NonNull
    @Override
    public TypePetsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_of_pets,null);
        return new TypePetsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TypePetsHolder holder, int position) {

        holder.textView_name_type_of_pets.setText(listModels.get(position).getName());
        Picasso.get().load(listModels.get(position).getImg()).into(holder.imageView_type_of_pets);

    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }
    public class TypePetsHolder extends RecyclerView.ViewHolder {
        public ImageView imageView_type_of_pets;
        public TextView textView_name_type_of_pets;

        public CardView cardView;
        public TypePetsHolder(@NonNull View itemView) {
            super(itemView);
            imageView_type_of_pets = itemView.findViewById(R.id.img_type_of_care);
            textView_name_type_of_pets = itemView.findViewById(R.id.tv_type_of_care);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });*/
        }
    }
}
