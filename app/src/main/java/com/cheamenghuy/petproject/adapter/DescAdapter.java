package com.cheamenghuy.petproject.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cheamenghuy.petproject.description.FoodFragment;
import com.cheamenghuy.petproject.description.HealthFragment;
import com.cheamenghuy.petproject.description.HospitalFragment;
import com.cheamenghuy.petproject.description.LivingFragment;

public class DescAdapter extends FragmentPagerAdapter {
    int numTab;
    public DescAdapter(@NonNull FragmentManager fm, int numTab) {
        super(fm);
        this.numTab = numTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new HealthFragment();
            case 1 : return new FoodFragment();
            case 2 : return new LivingFragment();
            case 3 : return  new HospitalFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return  numTab;
    }
}
