package com.cheamenghuy.petproject.care;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.adapter.CareAdapter;
import com.cheamenghuy.petproject.model.CareModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CareFragment extends Fragment implements CareAdapter.OnClickItemListener{

    RecyclerView recyclerView;
    CareAdapter adapter;
    ArrayList<CareModel> careModels;
    String[] name = {"Dog","Cat","Fish","Parrot"};
    int[] img = {R.drawable.dog,R.drawable.cat,R.drawable.fish,R.drawable.parot};

    View root;
    public CareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_care, container, false);

        recyclerView=root.findViewById(R.id.recycler_view);
        careModels = new ArrayList<>();
        for(int i = 0 ; i<4; i++){
            CareModel model = new CareModel();
            model.setName_type_of_care(name[i]);
            model.setImg_type_of_care(img[i]);
            careModels.add(model);
        }
        for(int i= 0  ; i<4 ;i++){

        Log.e( "onCreateView: array",careModels.get(i).getName_type_of_care().toString() );}
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),2));
        adapter = new CareAdapter(root.getContext(),careModels);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickItemListener(CareFragment.this);
        return root;
    }

    @Override
    public void onItemClick(int position) {

        getFragmentManager().beginTransaction().replace(R.id.frame_layout,new TypeofEachPetsFragment(position)).commit();
    }
}
