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
import android.widget.TextView;

import com.example.tisandg.americacup2019.Database.DatabaseAmericaCupAccesor;
import com.example.tisandg.americacup2019.Entities.TeamGroup;
import com.example.tisandg.americacup2019.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {

    private int idGroup;
    private List<TeamGroup> Group;
    private String TAG = "GroupFragment";
    private Context context;

    //Items layout
    TextView nameGroup;
    TextView nameTeamA, matchesTeamA, wonTeamA, drawnTeamA, lostTeamA, ptsTeamA;
    TextView nameTeamB, matchesTeamB, wonTeamB, drawnTeamB, lostTeamB, ptsTeamB;
    TextView nameTeamC, matchesTeamC, wonTeamC, drawnTeamC, lostTeamC, ptsTeamC;
    TextView nameTeamD, matchesTeamD, wonTeamD, drawnTeamD, lostTeamD, ptsTeamD;

    public GroupFragment() {
        // Required empty public constructor
    }

    @Override
    public String toString() {
        return "Group";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_group, container, false);
        Group = new ArrayList<TeamGroup>();

        nameGroup = view.findViewById(R.id.name_group);
        //First team
        nameTeamA = view.findViewById(R.id.name_team_a);
        matchesTeamA = view.findViewById(R.id.pj_team_a);
        wonTeamA = view.findViewById(R.id.pg_team_a);
        drawnTeamA = view.findViewById(R.id.pe_team_a);
        lostTeamA = view.findViewById(R.id.pp_team_a);
        ptsTeamA = view.findViewById(R.id.pt_team_a);

        //Second team
        nameTeamB = view.findViewById(R.id.name_team_b);
        matchesTeamB = view.findViewById(R.id.pj_team_b);
        wonTeamB = view.findViewById(R.id.pg_team_b);
        drawnTeamB = view.findViewById(R.id.pe_team_b);
        lostTeamB = view.findViewById(R.id.pp_team_b);
        ptsTeamB = view.findViewById(R.id.pt_team_b);

        //Third team
        nameTeamC = view.findViewById(R.id.name_team_c);
        matchesTeamC = view.findViewById(R.id.pj_team_c);
        wonTeamC = view.findViewById(R.id.pg_team_c);
        drawnTeamC = view.findViewById(R.id.pe_team_c);
        lostTeamC = view.findViewById(R.id.pp_team_c);
        ptsTeamC = view.findViewById(R.id.pt_team_c);

        //Fourth team
        nameTeamD = view.findViewById(R.id.name_team_d);
        matchesTeamD = view.findViewById(R.id.pj_team_d);
        wonTeamD = view.findViewById(R.id.pg_team_d);
        drawnTeamD = view.findViewById(R.id.pe_team_d);
        lostTeamD = view.findViewById(R.id.pp_team_d);
        ptsTeamD = view.findViewById(R.id.pt_team_d);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getGroup();
    }

    public void getGroup() {
        class GetGroup extends AsyncTask<Void, Void, List<TeamGroup>> {

            @Override
            protected List<TeamGroup> doInBackground(Void... voids) {
                Group = DatabaseAmericaCupAccesor
                        .getInstance(context).teamGroupDAO().findByGroup(idGroup);
                Log.d(TAG,"teams in group["+idGroup+"]: "+Group.size());
                return Group;
            }

            @Override
            protected void onPostExecute(List<TeamGroup> group) {
                super.onPostExecute(group);
                Log.d(TAG,"Actualizando adapter");
                if(group.size() != 0){

                    if(idGroup == 1){
                        nameGroup.setText("Group A");
                    }
                    if(idGroup == 2){
                        nameGroup.setText("Group B");
                    }
                    if(idGroup == 3){
                        nameGroup.setText("Group C");
                    }

                    TeamGroup team1, team2, team3, team4;
                    Log.d(TAG,"Size of group "+group.size());
                    team1 = group.get(0);
                    team2 = group.get(1);

                    //First team
                    nameTeamA.setText(team1.getTeamName());
                    matchesTeamA.setText(""+team1.getTeamGroup_matches());
                    wonTeamA.setText(""+team1.getTeamGroup_won());
                    drawnTeamA.setText(""+team1.getTeamGroup_drawn());
                    lostTeamA.setText(""+team1.getTeamGroup_lost());
                    ptsTeamA.setText(""+team1.getTeamGroup_pts());

                    //Second team
                    nameTeamB.setText(""+team2.getTeamName());
                    matchesTeamB.setText(""+team2.getTeamGroup_matches());
                    wonTeamB.setText(""+team2.getTeamGroup_won());
                    drawnTeamB.setText(""+team2.getTeamGroup_drawn());
                    lostTeamB.setText(""+team2.getTeamGroup_lost());
                    ptsTeamB.setText(""+team2.getTeamGroup_pts());
                    try {
                        team3 = group.get(2);
                        //Third team
                        nameTeamC.setText("" + team3.getTeamName());
                        matchesTeamC.setText("" + team3.getTeamGroup_matches());
                        wonTeamC.setText("" + team3.getTeamGroup_won());
                        drawnTeamC.setText("" + team3.getTeamGroup_drawn());
                        lostTeamC.setText("" + team3.getTeamGroup_lost());
                        ptsTeamC.setText("" + team3.getTeamGroup_pts());
                    }catch (Exception e){
                        Log.d(TAG,"Team 3 no found");
                        nameTeamC.setText("Paraguay");
                    }

                    try{
                        team4 = group.get(3);
                        //Fourth team
                        nameTeamD.setText(""+team4.getTeamName());
                        matchesTeamD.setText(""+team4.getTeamGroup_matches());
                        wonTeamD.setText(""+team4.getTeamGroup_won());
                        drawnTeamD.setText(""+team4.getTeamGroup_drawn());
                        lostTeamD.setText(""+team4.getTeamGroup_lost());
                        ptsTeamD.setText(""+team4.getTeamGroup_pts());
                    }catch (Exception e){
                        Log.d(TAG,"Team 4 no found");
                        nameTeamD.setText("Qatar");
                    }
                }
            }
        }
        GetGroup get = new GetGroup();
        get.execute();
    }

    public void update(){
        if(!isAdded()){
            return;
        }
        getGroup();
    }

    public void setGrupoSeleccionado(int idGroup) {
        this.idGroup = idGroup;
    }

    @Nullable
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
