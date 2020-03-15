package com.cheamenghuy.petproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TableLayout;

import com.cheamenghuy.petproject.adapter.DescAdapter;
import com.google.android.material.tabs.TabLayout;

public class PetDescription extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    DescAdapter descAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_description);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        descAdapter = new DescAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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

         }
}
