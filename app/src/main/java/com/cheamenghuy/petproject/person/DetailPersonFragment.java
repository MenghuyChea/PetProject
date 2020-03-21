package com.cheamenghuy.petproject.person;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheamenghuy.petproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailPersonFragment extends Fragment {

    public DetailPersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_person, container, false);
    }
}
