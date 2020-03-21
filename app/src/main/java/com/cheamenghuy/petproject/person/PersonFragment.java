package com.cheamenghuy.petproject.person;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheamenghuy.petproject.Login;
import com.cheamenghuy.petproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {

    String username;
    TextView textView_name;
    View root;
    LinearLayout linearLayout_btn_logout,linearLayout_btn_post;

    public PersonFragment(String username) {
        this.username = username;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_person, container, false);
        textView_name = root.findViewById(R.id.tv_name_profile);
        linearLayout_btn_logout = root.findViewById(R.id.btn_logout);
        linearLayout_btn_post = root.findViewById(R.id.btn_post);
        textView_name.setText(username);
        linearLayout_btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), Login.class);
                startActivity(intent);

            }
        });
        linearLayout_btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new DetailPersonFragment()).commit();
            }
        });
        return root;
    }
}
