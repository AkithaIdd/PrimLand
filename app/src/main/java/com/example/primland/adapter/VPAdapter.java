package com.example.primland.adapter;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Akitha Iddamalgoda on 8/11/2021.
 **/
public class VPAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private final ArrayList<String> fragmantTitle = new ArrayList<>();

    public VPAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void addFragment(Fragment fragment,String title) {

        fragmentArrayList.add(fragment);
        fragmantTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return fragmantTitle.get(position);
    }

}
