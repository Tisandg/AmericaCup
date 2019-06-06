package com.example.tisandg.americacup2019.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tisandg.americacup2019.Entities.Group;
import com.example.tisandg.americacup2019.Entities.TeamDetail;
import com.example.tisandg.americacup2019.R;
import com.example.tisandg.americacup2019.Recycler.AdapterGroupsRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListGroupsFragment extends Fragment {

    AdapterGroupsRecycler adaptador;
    RecyclerView mRecyclerView;
    List<Group> listData;
    String TAG = "ListGroups";

    public ListGroupsFragment() {
        // Required empty public constructor
    }

    @Override
    public String toString() {
        return "Groups";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list_groups, container, false);

        //Para el RecyclerView
        mRecyclerView = view.findViewById(R.id.recyclerView_groups);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.setHasFixedSize(true);
        listData = fillGroups();
        Log.d(TAG, "Numero de grupos: "+listData.size());
        adaptador = new AdapterGroupsRecycler(listData);
        mRecyclerView.setAdapter(adaptador);
        return view;
    }

    public List<Group> fillGroups(){
        List<Group> list = new ArrayList<Group>();
        int i = 0 ;
        for(i = 0;i<3;i++){
            Group group = new Group();
            List<TeamDetail> teams = new ArrayList<TeamDetail>();
            int j=0;
            for(j = 0;j<4;j++){
                TeamDetail team = new TeamDetail("Colombia",2,2,2,2,2);
                teams.add(team);
            }
            //group.setTeams(teams);
            list.add(group);
        }
        return list;
    }

}
