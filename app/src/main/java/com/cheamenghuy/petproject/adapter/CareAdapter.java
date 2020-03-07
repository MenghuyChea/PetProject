package com.cheamenghuy.petproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.holder.CareHolder;
import com.cheamenghuy.petproject.model.CareModel;

import java.util.ArrayList;

public class CareAdapter extends RecyclerView.Adapter<CareHolder> {
    Context context;
    ArrayList<CareModel> careModels;

    public CareAdapter(Context context, ArrayList<CareModel> careModels) {
        this.context = context;
        this.careModels = careModels;
    }

    @NonNull
    @Override
    public CareHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_care,null);
        return new CareHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CareHolder holder, int position) {

        holder.textView_name_care.setText(careModels.get(position).getName_type_of_care());
        holder.imageView_care.setImageResource(careModels.get(position).getImg_type_of_care());

    }

    @Override
    public int getItemCount() {
        return careModels.size();
    }
}