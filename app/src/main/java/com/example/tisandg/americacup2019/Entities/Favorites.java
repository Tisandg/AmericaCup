package com.example.tisandg.americacup2019.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Favorites {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int favorites_id;

    @NonNull
    private int team_id;

    public Favorites(int favorites_id, int team_id) {
        this.favorites_id = favorites_id;
        this.team_id = team_id;
    }

    public int getFavorites_id() {
        return favorites_id;
    }

    public void setFavorites_id(@NonNull int favorites_id) {
        this.favorites_id = favorites_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }
}
