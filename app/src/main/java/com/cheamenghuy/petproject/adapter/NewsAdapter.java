package com.cheamenghuy.petproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.holder.NewsHolder;
import com.cheamenghuy.petproject.model.NewsModel;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

    Context context;
    ArrayList<NewsModel> arrayList;
    RequestOptions options;

    public NewsAdapter(Context context, ArrayList<NewsModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shap).error(R.drawable.loading_shap);
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed,parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {

        Glide.with(context).load(arrayList.get(position).getImg_pro()).apply(options).into(holder.img_pro);
        holder.name_pro.setText(arrayList.get(position).getName_pro());
        holder.desc.setText(arrayList.get(position).getDesc());
        Glide.with(context).load(arrayList.get(position).getImg_post()).apply(options).into(holder.img_post);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
