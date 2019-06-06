package com.example.tisandg.americacup2019.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class TeamGroup {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int teamGroup_id;

    private String teamGroup_posicion;
    private int teamGroup_matches;
    private int teamGroup_win;
    private int teamGroup_draw;
    private int teamGroup_lose;
    private int teamGroup_pts;

    public TeamGroup(int teamGroup_id, String teamGroup_posicion, int teamGroup_matches, int teamGroup_win, int teamGroup_draw, int teamGroup_lose, int teamGroup_pts) {
        this.teamGroup_id = teamGroup_id;
        this.teamGroup_posicion = teamGroup_posicion;
        this.teamGroup_matches = teamGroup_matches;
        this.teamGroup_win = teamGroup_win;
        this.teamGroup_draw = teamGroup_draw;
        this.teamGroup_lose = teamGroup_lose;
        this.teamGroup_pts = teamGroup_pts;
    }

    public int getTeamGroup_id() {
        return teamGroup_id;
    }

    public void setTeamGroup_id(@NonNull int teamGroup_id) {
        this.teamGroup_id = teamGroup_id;
    }

    public String getTeamGroup_posicion() {
        return teamGroup_posicion;
    }

    public void setTeamGroup_posicion(String teamGroup_posicion) {
        this.teamGroup_posicion = teamGroup_posicion;
    }

    public int getTeamGroup_matches() {
        return teamGroup_matches;
    }

    public void setTeamGroup_matches(int teamGroup_matches) {
        this.teamGroup_matches = teamGroup_matches;
    }

    public int getTeamGroup_win() {
        return teamGroup_win;
    }

    public void setTeamGroup_win(int teamGroup_win) {
        this.teamGroup_win = teamGroup_win;
    }

    public int getTeamGroup_draw() {
        return teamGroup_draw;
    }

    public void setTeamGroup_draw(int teamGroup_draw) {
        this.teamGroup_draw = teamGroup_draw;
    }

    public int getTeamGroup_lose() {
        return teamGroup_lose;
    }

    public void setTeamGroup_lose(int teamGroup_lose) {
        this.teamGroup_lose = teamGroup_lose;
    }

    public int getTeamGroup_pts() {
        return teamGroup_pts;
    }

    public void setTeamGroup_pts(int teamGroup_pts) {
        this.teamGroup_pts = teamGroup_pts;
    }
}
