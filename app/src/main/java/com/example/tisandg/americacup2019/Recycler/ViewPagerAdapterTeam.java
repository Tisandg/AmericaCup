package com.example.tisandg.americacup2019.Recycler;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tisandg.americacup2019.Fragments.GroupFragment;
import com.example.tisandg.americacup2019.Fragments.MatchesFragment;

import java.util.List;

public class ViewPagerAdapterTeam extends FragmentStatePagerAdapter {

    private List<Fragment> listFragments;

    public ViewPagerAdapterTeam(List<Fragment> listFragment, FragmentManager fm){
        super(fm);
        this.listFragments = listFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listFragments.get(position).toString();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int i) {
        return listFragments.get(i);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    public void updateFragment(int posicion )
    {
        switch (posicion)
        {
            case 0:
                ((MatchesFragment)listFragments.get(posicion)).update();
                break;
            case 1:
                ((GroupFragment)listFragments.get(posicion)).update();
                break;
        }
    }
}
