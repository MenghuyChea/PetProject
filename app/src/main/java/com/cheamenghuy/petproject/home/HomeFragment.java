package com.cheamenghuy.petproject.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.adapter.CareAdapter;
import com.cheamenghuy.petproject.adapter.NewsAdapter;
import com.cheamenghuy.petproject.model.CareModel;
import com.cheamenghuy.petproject.model.NewsModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    ArrayList<NewsModel> careModels;
    View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.recycleView_home);
        careModels = new ArrayList<>();
        for(int i = 0 ; i<4; i++){
            NewsModel model = new NewsModel();
            model.setDesc("Hello my friends.");
            model.setImg_pro("https://images.pexels.com/photos/744667/pexels-photo-744667.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
            model.setImg_post("https://images.pexels.com/photos/744667/pexels-photo-744667.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
            model.setName_pro("Soy Sin");
            careModels.add(model);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new NewsAdapter(view.getContext(),careModels);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
