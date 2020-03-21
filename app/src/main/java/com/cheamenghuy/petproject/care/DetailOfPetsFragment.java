package com.cheamenghuy.petproject.care;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.adapter.DescAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailOfPetsFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    DescAdapter descAdapter;
    View root;
    Button buttonBackDes;
    int typePet;

    public DetailOfPetsFragment(int num) {
        typePet = num;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_detail_of_pets, container, false);
        tabLayout = root.findViewById(R.id.tab_layout);
        viewPager = root.findViewById(R.id.view_pager);
        buttonBackDes = root.findViewById(R.id.btn_backDes);
        descAdapter = new DescAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(descAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        buttonBackDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new TypeofEachPetsFragment(typePet)).commit();
            }
        });

        return root;
    }
}
