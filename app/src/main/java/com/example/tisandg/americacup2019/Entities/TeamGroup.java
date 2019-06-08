package com.example.tisandg.americacup2019.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class TeamGroup {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int teamGroup_id;
    private String teamName;
    private int teamGroup_rank;
    private int teamGroup_matches;
    private int teamGroup_won;
    private int teamGroup_drawn;
    private int teamGroup_lost;
    private int teamGroup_pts;

    public TeamGroup(int teamGroup_id, String teamName, int teamGroup_rank, int teamGroup_matches, int teamGroup_won, int teamGroup_drawn, int teamGroup_lost, int teamGroup_pts) {
        this.teamGroup_id = teamGroup_id;
        this.teamName = teamName;
        this.teamGroup_rank = teamGroup_rank;
        this.teamGroup_matches = teamGroup_matches;
        this.teamGroup_won = teamGroup_won;
        this.teamGroup_drawn = teamGroup_drawn;
        this.teamGroup_lost = teamGroup_lost;
        this.teamGroup_pts = teamGroup_pts;
    }

    public int getTeamGroup_id() {
        return teamGroup_id;
    }

    public void setTeamGroup_id(@NonNull int teamGroup_id) {
        this.teamGroup_id = teamGroup_id;
    }

    public int getTeamGroup_rank() {
        return teamGroup_rank;
    }

    public void setTeamGroup_rank(int teamGroup_rank) {
        this.teamGroup_rank = teamGroup_rank;
    }

    public int getTeamGroup_matches() {
        return teamGroup_matches;
    }

    public void setTeamGroup_matches(int teamGroup_matches) {
        this.teamGroup_matches = teamGroup_matches;
    }

    public int getTeamGroup_won() {
        return teamGroup_won;
    }

    public void setTeamGroup_won(int teamGroup_won) {
        this.teamGroup_won = teamGroup_won;
    }

    public int getTeamGroup_drawn() {
        return teamGroup_drawn;
    }

    public void setTeamGroup_drawn(int teamGroup_drawn) {
        this.teamGroup_drawn = teamGroup_drawn;
    }

    public int getTeamGroup_lost() {
        return teamGroup_lost;
    }

    public void setTeamGroup_lost(int teamGroup_lost) {
        this.teamGroup_lost = teamGroup_lost;
    }

    public int getTeamGroup_pts() {
        return teamGroup_pts;
    }

    public void setTeamGroup_pts(int teamGroup_pts) {
        this.teamGroup_pts = teamGroup_pts;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
