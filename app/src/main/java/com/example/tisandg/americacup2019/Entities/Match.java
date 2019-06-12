package com.example.tisandg.americacup2019.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

@Entity
@TypeConverters(DateConverter .class)
public class Match {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int match_id;

    private int match_id_teamA;
    private int match_id_teamB;
    private String teamA;
    private String teamB;
    private String score;
    private String status;
    //private Date match_date;
    private String match_date;
    private String match_hour;
    private String match_stadium;
    private int group_id;

    public Match(int match_id, int match_id_teamA, String teamA, int match_id_teamB, String teamB,
                 String match_date, String match_hour, String match_stadium, int group_id) {
        this.match_id = match_id;
        this.match_id_teamA = match_id_teamA;
        this.match_id_teamB = match_id_teamB;
        this.match_date = match_date;
        this.match_hour = match_hour;
        this.match_stadium = match_stadium;
        this.group_id = group_id;
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(@NonNull int match_id) {
        this.match_id = match_id;
    }

    public int getMatch_id_teamA() {
        return match_id_teamA;
    }

    public void setMatch_id_teamA(int match_id_teamA) {
        this.match_id_teamA = match_id_teamA;
    }

    public int getMatch_id_teamB() {
        return match_id_teamB;
    }

    public void setMatch_id_teamB(int match_id_teamB) {
        this.match_id_teamB = match_id_teamB;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getMatch_hour() {
        return match_hour;
    }

    public void setMatch_hour(String match_hour) {
        this.match_hour = match_hour;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getMatch_stadium() {
        return match_stadium;
    }

    public void setMatch_stadium(String match_stadium) {
        this.match_stadium = match_stadium;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
