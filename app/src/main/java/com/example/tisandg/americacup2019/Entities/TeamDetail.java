package com.example.tisandg.americacup2019.Entities;

public class TeamDetail {

    private String nameTeam;
    private int PJ,PG,PE,PP,PT;

    public TeamDetail(String nameTeam, int PJ, int PG, int PE, int PP, int PT) {
        this.nameTeam = nameTeam;
        this.PJ = PJ;
        this.PG = PG;
        this.PE = PE;
        this.PP = PP;
        this.PT = PT;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public int getPJ() {
        return PJ;
    }

    public void setPJ(int PJ) {
        this.PJ = PJ;
    }

    public int getPG() {
        return PG;
    }

    public void setPG(int PG) {
        this.PG = PG;
    }

    public int getPE() {
        return PE;
    }

    public void setPE(int PE) {
        this.PE = PE;
    }

    public int getPP() {
        return PP;
    }

    public void setPP(int PP) {
        this.PP = PP;
    }

    public int getPT() {
        return PT;
    }

    public void setPT(int PT) {
        this.PT = PT;
    }
}
