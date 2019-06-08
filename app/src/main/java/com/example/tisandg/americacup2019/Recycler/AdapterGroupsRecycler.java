package com.example.tisandg.americacup2019.Recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tisandg.americacup2019.Entities.TeamGroup;
import com.example.tisandg.americacup2019.R;

import java.util.List;

public class AdapterGroupsRecycler extends RecyclerView.Adapter<AdapterGroupsRecycler.ItemViewHolder> {

    private List<TeamGroup> groupA;
    private List<TeamGroup> groupB;
    private List<TeamGroup> groupC;

    public AdapterGroupsRecycler(List<TeamGroup>listDataA, List<TeamGroup>listDataB, List<TeamGroup>listDataC) {
        this.groupA = listDataA;
        this.groupB = listDataB;
        this.groupC = listDataC;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_group,
                viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder view, int i) {
        //List<TeamDetail> teams = listMatches.get(i).getTeams();
        /*First Team*/
        /*view.nameA.setText(teams.get(0).getNameTeam());
        view.PJA.setText(""+teams.get(0).getPJ());
        view.PGA.setText(""+teams.get(0).getPG());
        view.PEA.setText(""+teams.get(0).getPE());
        view.PPA.setText(""+teams.get(0).getPP());
        view.PTA.setText(""+teams.get(0).getPT());

        //Second
        view.nameB.setText(teams.get(1).getNameTeam());
        view.PJB.setText(""+teams.get(1).getPJ());
        view.PGB.setText(""+teams.get(1).getPG());
        view.PEB.setText(""+teams.get(1).getPE());
        view.PPB.setText(""+teams.get(1).getPP());
        view.PTB.setText(""+teams.get(1).getPT());

        //Third
        view.nameC.setText(teams.get(2).getNameTeam());
        view.PJC.setText(""+teams.get(2).getPJ());
        view.PGC.setText(""+teams.get(2).getPG());
        view.PEC.setText(""+teams.get(2).getPE());
        view.PPC.setText(""+teams.get(2).getPP());
        view.PTC.setText(""+teams.get(2).getPT());

        //Fourth
        view.nameD.setText(teams.get(3).getNameTeam());
        view.PJD.setText(""+teams.get(3).getPJ());
        view.PGD.setText(""+teams.get(3).getPG());
        view.PED.setText(""+teams.get(3).getPE());
        view.PPD.setText(""+teams.get(3).getPP());
        view.PTD.setText(""+teams.get(3).getPT());*/
    }

    @Override
    public int getItemCount() {
        return groupA.size()+groupB.size()+groupC.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView nameA, PJA,PGA,PEA,PPA,PTA;
        TextView nameB, PJB,PGB,PEB,PPB,PTB;
        TextView nameC, PJC,PGC,PEC,PPC,PTC;
        TextView nameD, PJD,PGD,PED,PPD,PTD;

        public ItemViewHolder(@NonNull View view) {
            super(view);
            nameA = view.findViewById(R.id.name_team_a);
            PJA = view.findViewById(R.id.pj_team_a);
            PGA = view.findViewById(R.id.pg_team_a);
            PEA = view.findViewById(R.id.pe_team_a);
            PPA = view.findViewById(R.id.pp_team_a);
            PTA = view.findViewById(R.id.pt_team_a);

            nameB = view.findViewById(R.id.name_team_b);
            PJB = view.findViewById(R.id.pj_team_b);
            PGB = view.findViewById(R.id.pg_team_b);
            PEB = view.findViewById(R.id.pe_team_b);
            PPB = view.findViewById(R.id.pp_team_b);
            PTB = view.findViewById(R.id.pt_team_b);

            nameC = view.findViewById(R.id.name_team_c);
            PJC = view.findViewById(R.id.pj_team_c);
            PGC = view.findViewById(R.id.pg_team_c);
            PEC = view.findViewById(R.id.pe_team_c);
            PPC = view.findViewById(R.id.pp_team_c);
            PTC = view.findViewById(R.id.pt_team_c);

            nameD = view.findViewById(R.id.name_team_d);
            PJD = view.findViewById(R.id.pj_team_d);
            PGD = view.findViewById(R.id.pg_team_d);
            PED = view.findViewById(R.id.pe_team_d);
            PPD = view.findViewById(R.id.pp_team_d);
            PTD = view.findViewById(R.id.pt_team_d);
        }
    }

    public void setGroupA(List<TeamGroup> groupA) {
        this.groupA = groupA;
    }

    public void setGroupB(List<TeamGroup> groupB) {
        this.groupB = groupB;
    }

    public void setGroupC(List<TeamGroup> groupC) {
        this.groupC = groupC;
    }
}
