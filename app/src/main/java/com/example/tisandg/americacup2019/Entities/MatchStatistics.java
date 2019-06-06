package com.example.tisandg.americacup2019.Entities;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"match_id", "team_id"})
public class MatchStatistics {

    private int match_yellow;
    private int match_red;
    private int match_fouls;
    private int match_offsides;
    private int match_corners;
    private int match_possession;

    //Primary keys
    @NonNull
    private int match_id;

    @NonNull
    private int team_id;

    public MatchStatistics(int match_yellow, int match_red, int match_fouls, int match_offsides, int match_corners, int match_possession, int match_id, int team_id) {
        this.match_yellow = match_yellow;
        this.match_red = match_red;
        this.match_fouls = match_fouls;
        this.match_offsides = match_offsides;
        this.match_corners = match_corners;
        this.match_possession = match_possession;
        this.match_id = match_id;
        this.team_id = team_id;
    }

    public int getMatch_yellow() {
        return match_yellow;
    }

    public void setMatch_yellow(int match_yellow) {
        this.match_yellow = match_yellow;
    }

    public int getMatch_red() {
        return match_red;
    }

    public void setMatch_red(int match_red) {
        this.match_red = match_red;
    }

    public int getMatch_fouls() {
        return match_fouls;
    }

    public void setMatch_fouls(int match_fouls) {
        this.match_fouls = match_fouls;
    }

    public int getMatch_offsides() {
        return match_offsides;
    }

    public void setMatch_offsides(int match_offsides) {
        this.match_offsides = match_offsides;
    }

    public int getMatch_corners() {
        return match_corners;
    }

    public void setMatch_corners(int match_corners) {
        this.match_corners = match_corners;
    }

    public int getMatch_possession() {
        return match_possession;
    }

    public void setMatch_possession(int match_possession) {
        this.match_possession = match_possession;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(@NonNull int match_id) {
        this.match_id = match_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(@NonNull int team_id) {
        this.team_id = team_id;
    }
}
