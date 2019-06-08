package com.example.tisandg.americacup2019.Fragments;

import com.example.tisandg.americacup2019.Entities.TeamGroup;

import java.util.ArrayList;
import java.util.List;

public class GroupWithTeams {

    private String name;
    private List<TeamGroup> group;

    public GroupWithTeams(String name, List<TeamGroup> group) {
        this.name = name;
        this.group = group;
    }

    public GroupWithTeams() {
        this.group = new ArrayList<TeamGroup>();
    }

    public List<TeamGroup> getGroup() {
        return group;
    }

    public void setGroup(List<TeamGroup> group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
