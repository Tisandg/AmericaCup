package com.example.tisandg.americacup2019.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Team {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int team_id;

    private String team_name;
    private String team_image;
    private int group_id;

    public Team(int team_id, String team_name, String team_image, int group_id) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_image = team_image;
        this.group_id = group_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(@NonNull int team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_image() {
        return team_image;
    }

    public void setTeam_image(String team_image) {
        this.team_image = team_image;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
