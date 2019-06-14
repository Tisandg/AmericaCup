package com.example.tisandg.americacup2019.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.Team;
import com.example.tisandg.americacup2019.Entities.TeamGroup;
import com.example.tisandg.americacup2019.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListGroupsFragment extends Fragment {

    List<TeamGroup> GroupA, GroupB, GroupC;
    String TAG = "ListGroups";
    private Context mContext;

    GroupAdapter adapter;
    private GroupWithTeams[] groups = new GroupWithTeams[3];

    /**0 todos los grupos
     * 1 grupo A
     * 2 grupo B
     * 3 grupo C
     */
    private int grupoSeleccionado;

    //Items Layout
    private ListView listViewGroups;

    private final static int idGroupA = 1;
    private final static int idGroupB = 2;
    private final static int idGroupC = 3;

    public ListGroupsFragment() {
        // Required empty public constructor
        grupoSeleccionado = 0;
    }

    @Override
    public String toString() {
        return mContext.getString(R.string.title_fragment_groups);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list_groups, container, false);

        GroupA = new ArrayList<TeamGroup>();
        GroupB = new ArrayList<TeamGroup>();
        GroupC = new ArrayList<TeamGroup>();

        if(grupoSeleccionado == 0){
            getGroups(idGroupA);
            getGroups(idGroupB);
            getGroups(idGroupC);
        }else{
            switch (grupoSeleccionado){
                case 1: getGroups(idGroupA);    break;
                case 2: getGroups(idGroupB);    break;
                case 3: getGroups(idGroupC);    break;
            }
        }

        adapter = new GroupAdapter(getActivity(), groups);
        listViewGroups = view.findViewById(R.id.list_view_groups);
        listViewGroups.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void update(){
        if(!isAdded()){
            return;
        }
        Log.d(TAG,"Actualizando fragment");
        if(grupoSeleccionado == 0){
            GroupA = new ArrayList<TeamGroup>();
            GroupB = new ArrayList<TeamGroup>();
            GroupC = new ArrayList<TeamGroup>();
            getGroups(idGroupA);
            getGroups(idGroupB);
            getGroups(idGroupC);
        }else{
            switch (grupoSeleccionado){
                case 1:
                    GroupA = new ArrayList<TeamGroup>();
                    getGroups(idGroupA);
                    break;

                case 2:
                    GroupB = new ArrayList<TeamGroup>();
                    getGroups(idGroupB);
                    break;

                case 3:
                    GroupC = new ArrayList<TeamGroup>();
                    getGroups(idGroupC);
                    break;
            }
        }
    }

    public void getGroups(final int idGrupo) {
        class GetGroups extends AsyncTask<Void, Void, List<TeamGroup>> {

            @Override
            protected List<TeamGroup> doInBackground(Void... voids) {
                List<TeamGroup> group = DatabaseAmericaCupAccesor
                        .getInstance(getActivity().getApplication()).teamGroupDAO().findByGroup(idGrupo);
                Log.d(TAG,"teams in group["+idGrupo+"]: "+group.size());
                return group;
            }

            @Override
            protected void onPostExecute(List<TeamGroup> group) {
                super.onPostExecute(group);
                Log.d(TAG,"Actualizando adapter");
                if(group.size() != 0){
                    Log.d(TAG,"Adding teams from group "+idGrupo);
                    switch (idGrupo){
                        case idGroupA:
                            for(TeamGroup t:group){
                                GroupA.add(t);
                            }
                            adapter.setGroupA(GroupA);
                            break;

                        case idGroupB:
                            for(TeamGroup t:group){
                                GroupB.add(t);
                            }
                            adapter.setGroupB(GroupB);
                            break;

                        case idGroupC:
                            for(TeamGroup t:group){
                                GroupC.add(t);
                            }
                            adapter.setGroupC(GroupC);
                            break;
                    }
                    adapter.notifyDataSetChanged();
                }else{
                    Log.d(TAG,"Set Deafult values");
                    switch (idGrupo){
                        case idGroupA:
                            GroupA = new ArrayList<TeamGroup>();
                            getTeamsFromGroup(idGroupA);
                            break;

                        case idGroupB:
                            GroupB = new ArrayList<TeamGroup>();
                            getTeamsFromGroup(idGroupB);
                            break;

                        case idGroupC:
                            GroupC = new ArrayList<TeamGroup>();
                            getTeamsFromGroup(idGroupC);
                            break;
                    }
                }
            }
        }
        GetGroups get = new GetGroups();
        get.execute();
    }

    /**
     * Obtain teams from a group and define default value in their standing
     * @param idGroup
     */
    public void getTeamsFromGroup(final int idGroup){
        class GetTeams extends AsyncTask<Void, Void, List<Team>> {

            @Override
            protected List<Team> doInBackground(Void... voids) {
                List<Team> group = DatabaseAmericaCupAccesor
                        .getInstance(getActivity().getApplication()).teamDAO().findByGroup(idGroup);
                Log.d(TAG,"teams of group["+idGroup+"]: "+group.size());
                return group;
            }

            @Override
            protected void onPostExecute(List<Team> group) {
                super.onPostExecute(group);
                Log.d(TAG,"Actualizando adapter");
                if(group.size() != 0){
                    List<TeamGroup> auxGroup = new ArrayList<>();
                    for(Team t:group){
                        TeamGroup teamGroup = new TeamGroup(idGroup,t.getTeam_name(),1,0,0,0,0,0);
                        auxGroup.add(teamGroup);
                    }
                    switch (idGroup){
                        case idGroupA:
                            for(TeamGroup t:auxGroup){
                                GroupA.add(t);
                            }
                            adapter.setGroupA(GroupA);
                            break;

                        case idGroupB:
                            for(TeamGroup t:auxGroup){
                                GroupB.add(t);
                            }
                            adapter.setGroupB(GroupB);
                            break;

                        case idGroupC:
                            for(TeamGroup t:auxGroup){
                                GroupC.add(t);
                            }
                            Log.d(TAG,"Añdiendo grupo C. Num: "+GroupC.size());
                            adapter.setGroupC(GroupC);
                            break;
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }
        GetTeams get = new GetTeams();
        get.execute();
    }

    public int getGrupoSeleccionado() {
        return grupoSeleccionado;
    }

    public void setGrupoSeleccionado(int grupoSeleccionado) {
        this.grupoSeleccionado = grupoSeleccionado;
    }

    public void setContext(Context mainActivity) {
        this.mContext = mainActivity;
    }
}
