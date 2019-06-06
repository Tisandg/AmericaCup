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
public class GroupFragment extends Fragment {

    public GroupFragment() {
        // Required empty public constructor
    }

    @Override
    public String toString() {
        return "Groups";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.cardview_group, container, false);
        return view;
    }

}
