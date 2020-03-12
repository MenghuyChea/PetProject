package com.cheamenghuy.petproject.notification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.adapter.NotificationAdapter;
import com.cheamenghuy.petproject.model.NotificationModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {
    RecyclerView recyclerView;
    NotificationAdapter adapter;
    ArrayList<NotificationModel> listModels;

    View root;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerView = root.findViewById(R.id.recycleView_notification);
        listModels = new ArrayList<>();
        for(int i=0 ; i<10 ; i++){
            NotificationModel model = new NotificationModel();
            model.setImg(R.drawable.ic_launcher_foreground);
            model.setName("Von Seyha");
            model.setDescription("Hello world");
            model.setTime("12 March 2019");
            listModels.add(model);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter = new NotificationAdapter(root.getContext(),listModels);
        recyclerView.setAdapter(adapter);
        return root;
    }
}
