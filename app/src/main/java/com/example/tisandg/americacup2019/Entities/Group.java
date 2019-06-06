package com.example.tisandg.americacup2019.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Group{

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int group_id;

    @NonNull
    private String group_name;

    public Group(){}

    public Group(int group_id, String group_name) {
        this.group_id = group_id;
        this.group_name = group_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(@NonNull int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
}
