package com.example.tisandg.americacup2019.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Match {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int match_id;

    private int match_teamA;
    private int match_teamB;
    private int match_goalsA;
    private int match_goalsB;
    private String match_date;
    private String match_hour;
    private String match_stadium;
    private boolean match_finalized;
    private int group_id;

    public Match(int match_id, int match_teamA, int match_teamB, int match_goalsA, int match_goalsB, String match_date, String match_hour, String match_stadium, boolean match_finalized, int group_id) {
        this.match_id = match_id;
        this.match_teamA = match_teamA;
        this.match_teamB = match_teamB;
        this.match_goalsA = match_goalsA;
        this.match_goalsB = match_goalsB;
        this.match_date = match_date;
        this.match_hour = match_hour;
        this.match_stadium = match_stadium;
        this.match_finalized = match_finalized;
        this.group_id = group_id;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(@NonNull int match_id) {
        this.match_id = match_id;
    }

    public int getMatch_teamA() {
        return match_teamA;
    }

    public void setMatch_teamA(int match_teamA) {
        this.match_teamA = match_teamA;
    }

    public int getMatch_teamB() {
        return match_teamB;
    }

    public void setMatch_teamB(int match_teamB) {
        this.match_teamB = match_teamB;
    }

    public int getMatch_goalsA() {
        return match_goalsA;
    }

    public void setMatch_goalsA(int match_goalsA) {
        this.match_goalsA = match_goalsA;
    }

    public int getMatch_goalsB() {
        return match_goalsB;
    }

    public void setMatch_goalsB(int match_goalsB) {
        this.match_goalsB = match_goalsB;
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

    public boolean isMatch_finalized() {
        return match_finalized;
    }

    public void setMatch_finalized(boolean match_finalized) {
        this.match_finalized = match_finalized;
    }
}
