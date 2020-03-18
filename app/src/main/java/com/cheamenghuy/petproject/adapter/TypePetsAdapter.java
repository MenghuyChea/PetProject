package com.cheamenghuy.petproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.holder.TypePetsHolder;
import com.cheamenghuy.petproject.model.TypePetsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TypePetsAdapter extends RecyclerView.Adapter<TypePetsHolder> {

    Context context;
    ArrayList<TypePetsModel> listModels;

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

       /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager;
                fragmentManager.beginTransaction().replace(R.id.frame_layout)
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }
}
