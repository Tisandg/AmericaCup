package com.example.tisandg.americacup2019;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tisandg.americacup2019.Fragments.FavoritesFragment;
import com.example.tisandg.americacup2019.Fragments.ListGroupsFragment;
import com.example.tisandg.americacup2019.Fragments.MatchesFragment;

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
                ((ListGroupsFragment)listFragments.get(posicion)).update();
                break;
            case 2:
                ((FavoritesFragment)listFragments.get(posicion)).update();
                break;
        }
    }
}
