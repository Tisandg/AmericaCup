package com.example.tisandg.americacup2019.Fragments;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tisandg.americacup2019.Entities.TeamGroup;
import com.example.tisandg.americacup2019.R;

import java.util.List;

class ViewHolder{
    TextView nameGroup;
    TextView nameTeamA, matchesTeamA, wonTeamA, drawnTeamA, lostTeamA, ptsTeamA;
    TextView nameTeamB, matchesTeamB, wonTeamB, drawnTeamB, lostTeamB, ptsTeamB;
    TextView nameTeamC, matchesTeamC, wonTeamC, drawnTeamC, lostTeamC, ptsTeamC;
    TextView nameTeamD, matchesTeamD, wonTeamD, drawnTeamD, lostTeamD, ptsTeamD;
}

public class GroupAdapter extends BaseAdapter {

    private Activity activity;
    private List<GroupWithTeams> groups;
    private String TAG = "GroupAdapter";

    public GroupAdapter(Activity activity, List<GroupWithTeams> groups) {
        this.activity = activity;
        this.groups = groups;
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater inflater = this.activity.getLayoutInflater();
            view = inflater.inflate(R.layout.cardview_group, null);
            viewHolder = new ViewHolder();

            viewHolder.nameGroup = view.findViewById(R.id.name_group);

            //First team
            viewHolder.nameTeamA = view.findViewById(R.id.name_team_a);
            viewHolder.matchesTeamA = view.findViewById(R.id.pj_team_a);
            viewHolder.wonTeamA = view.findViewById(R.id.pg_team_a);
            viewHolder.drawnTeamA = view.findViewById(R.id.pe_team_a);
            viewHolder.lostTeamA = view.findViewById(R.id.pp_team_a);
            viewHolder.ptsTeamA = view.findViewById(R.id.pt_team_a);

            //Second team
            viewHolder.nameTeamB = view.findViewById(R.id.name_team_b);
            viewHolder.matchesTeamB = view.findViewById(R.id.pj_team_b);
            viewHolder.wonTeamB = view.findViewById(R.id.pg_team_b);
            viewHolder.drawnTeamB = view.findViewById(R.id.pe_team_b);
            viewHolder.lostTeamB = view.findViewById(R.id.pp_team_b);
            viewHolder.ptsTeamB = view.findViewById(R.id.pt_team_b);

            //Third team
            viewHolder.nameTeamC = view.findViewById(R.id.name_team_c);
            viewHolder.matchesTeamC = view.findViewById(R.id.pj_team_c);
            viewHolder.wonTeamC = view.findViewById(R.id.pg_team_c);
            viewHolder.drawnTeamC = view.findViewById(R.id.pe_team_c);
            viewHolder.lostTeamC = view.findViewById(R.id.pp_team_c);
            viewHolder.ptsTeamC = view.findViewById(R.id.pt_team_c);

            //Fourth team
            viewHolder.nameTeamD = view.findViewById(R.id.name_team_d);
            viewHolder.matchesTeamD = view.findViewById(R.id.pj_team_d);
            viewHolder.wonTeamD = view.findViewById(R.id.pg_team_d);
            viewHolder.drawnTeamD = view.findViewById(R.id.pe_team_d);
            viewHolder.lostTeamD = view.findViewById(R.id.pp_team_d);
            viewHolder.ptsTeamD = view.findViewById(R.id.pt_team_d);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.nameGroup.setText(groups.get(position).getName());

        TeamGroup team1, team2, team3, team4;
        Log.d(TAG,"Size of group "+groups.get(position).getGroup().size());
        team1 = groups.get(position).getGroup().get(0);
        team2 = groups.get(position).getGroup().get(1);

        //First team
        viewHolder.nameTeamA.setText(team1.getTeamName());
        viewHolder.matchesTeamA.setText(""+team1.getTeamGroup_matches());
        viewHolder.wonTeamA.setText(""+team1.getTeamGroup_won());
        viewHolder.drawnTeamA.setText(""+team1.getTeamGroup_drawn());
        viewHolder.lostTeamA.setText(""+team1.getTeamGroup_lost());
        viewHolder.ptsTeamA.setText(""+team1.getTeamGroup_pts());

        //Second team
        viewHolder.nameTeamB.setText(""+team2.getTeamName());
        viewHolder.matchesTeamB.setText(""+team2.getTeamGroup_matches());
        viewHolder.wonTeamB.setText(""+team2.getTeamGroup_won());
        viewHolder.drawnTeamB.setText(""+team2.getTeamGroup_drawn());
        viewHolder.lostTeamB.setText(""+team2.getTeamGroup_lost());
        viewHolder.ptsTeamB.setText(""+team2.getTeamGroup_pts());
        try {
            team3 = groups.get(position).getGroup().get(2);
            //Third team
            viewHolder.nameTeamC.setText("" + team3.getTeamName());
            viewHolder.matchesTeamC.setText("" + team3.getTeamGroup_matches());
            viewHolder.wonTeamC.setText("" + team3.getTeamGroup_won());
            viewHolder.drawnTeamC.setText("" + team3.getTeamGroup_drawn());
            viewHolder.lostTeamC.setText("" + team3.getTeamGroup_lost());
            viewHolder.ptsTeamC.setText("" + team3.getTeamGroup_pts());
        }catch (Exception e){
            Log.d(TAG,"Team 3 no found");
            viewHolder.nameTeamC.setText("Paraguay");
        }

        try{
            team4 = groups.get(position).getGroup().get(3);
            //Fourth team
            viewHolder.nameTeamD.setText(""+team4.getTeamName());
            viewHolder.matchesTeamD.setText(""+team4.getTeamGroup_matches());
            viewHolder.wonTeamD.setText(""+team4.getTeamGroup_won());
            viewHolder.drawnTeamD.setText(""+team4.getTeamGroup_drawn());
            viewHolder.lostTeamD.setText(""+team4.getTeamGroup_lost());
            viewHolder.ptsTeamD.setText(""+team4.getTeamGroup_pts());
        }catch (Exception e){
            Log.d(TAG,"Team 4 no found");
            viewHolder.nameTeamD.setText("Qatar");
        }

        return view;
    }


    public void setGroupA(List<TeamGroup> group) {
        this.groups.add(0, new GroupWithTeams("Group A", group));
    }
    public void setGroupB(List<TeamGroup> group) {
        this.groups.add(1, new GroupWithTeams("Group B", group));
    }
    public void setGroupC(List<TeamGroup> group) {
        this.groups.add(2, new GroupWithTeams("Group C", group));
    }
}
