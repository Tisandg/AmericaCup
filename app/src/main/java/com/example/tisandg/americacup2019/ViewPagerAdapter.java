package com.example.tisandg.americacup2019;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> listFragments;


    public ViewPagerAdapter(List<Fragment> listFragment, FragmentManager fm){
        super(fm);
        this.listFragments = listFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listFragments.get(position).toString();
    }

    @Override
    public Fragment getItem(int i) {
        return listFragments.get(i);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}
