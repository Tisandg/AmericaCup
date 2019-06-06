package com.example.tisandg.americacup2019.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tisandg.americacup2019.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {


    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public String toString() {
        return "Favorities";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

}
