package com.cheamenghuy.petproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.model.CareModel;

import java.util.ArrayList;

public class CareAdapter extends RecyclerView.Adapter<CareAdapter.CareHolder> {
    FragmentManager fragmentManager;
    Context context;
    ArrayList<CareModel> careModels;
    OnClickItemListener mListener;

    public interface OnClickItemListener{
        public void onItemClick(int position);

    }

    public void setOnClickItemListener(OnClickItemListener mListener) {
        this.mListener = mListener;
    }

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
    public void onBindViewHolder(@NonNull CareHolder holder, final int position) {

        holder.textView_name_care.setText(careModels.get(position).getName_type_of_care());
        holder.imageView_care.setImageResource(careModels.get(position).getImg_type_of_care());

    }

    @Override
    public int getItemCount() {
        return careModels.size();
    }
    public class CareHolder extends RecyclerView.ViewHolder {

        public ImageView imageView_care;
        public TextView textView_name_care;
        public CareHolder(@NonNull View itemView) {
            super(itemView);
            imageView_care = itemView.findViewById(R.id.img_care);
            textView_name_care = itemView.findViewById(R.id.tv_name_care);
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
        }
    }
}
