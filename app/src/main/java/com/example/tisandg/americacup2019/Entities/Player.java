package com.example.tisandg.americacup2019.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Player {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int player_id;

    private String player_name;
    private String player_image;
    private String player_position;
    private String player_description;
    private String player_birthdate;
    private float player_height;
    private float player_weight;

    public Player(int player_id, String player_name, String player_image, String player_position, String player_description, String player_birthdate, float player_height, float player_weight) {
        this.player_id = player_id;
        this.player_name = player_name;
        this.player_image = player_image;
        this.player_position = player_position;
        this.player_description = player_description;
        this.player_birthdate = player_birthdate;
        this.player_height = player_height;
        this.player_weight = player_weight;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(@NonNull int player_id) {
        this.player_id = player_id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_image() {
        return player_image;
    }

    public void setPlayer_image(String player_image) {
        this.player_image = player_image;
    }

    public String getPlayer_position() {
        return player_position;
    }

    public void setPlayer_position(String player_position) {
        this.player_position = player_position;
    }

    public String getPlayer_description() {
        return player_description;
    }

    public void setPlayer_description(String player_description) {
        this.player_description = player_description;
    }

    public String getPlayer_birthdate() {
        return player_birthdate;
    }

    public void setPlayer_birthdate(String player_birthdate) {
        this.player_birthdate = player_birthdate;
    }

    public float getPlayer_height() {
        return player_height;
    }

    public void setPlayer_height(float player_height) {
        this.player_height = player_height;
    }

    public float getPlayer_weight() {
        return player_weight;
    }

    public void setPlayer_weight(float player_weight) {
        this.player_weight = player_weight;
    }
}
