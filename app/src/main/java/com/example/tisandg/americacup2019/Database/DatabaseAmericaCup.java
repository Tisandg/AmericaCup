package com.example.tisandg.americacup2019.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.tisandg.americacup2019.Entities.Favorites;
import com.example.tisandg.americacup2019.Entities.Group;
import com.example.tisandg.americacup2019.Entities.Match;
import com.example.tisandg.americacup2019.Entities.MatchStatistics;
import com.example.tisandg.americacup2019.Entities.Player;
import com.example.tisandg.americacup2019.Entities.Team;
import com.example.tisandg.americacup2019.Entities.TeamGroup;
import com.example.tisandg.americacup2019.EntitiesDAO.FavoritiesDAO;
import com.example.tisandg.americacup2019.EntitiesDAO.GroupDAO;
import com.example.tisandg.americacup2019.EntitiesDAO.MatchDAO;
import com.example.tisandg.americacup2019.EntitiesDAO.MatchStatisticsDAO;
import com.example.tisandg.americacup2019.EntitiesDAO.PlayerDAO;
import com.example.tisandg.americacup2019.EntitiesDAO.TeamDAO;
import com.example.tisandg.americacup2019.EntitiesDAO.TeamGroupDAO;

@Database(entities = {Favorites.class, Group.class, Match.class,
        MatchStatistics.class, Player.class, Team.class, TeamGroup.class}, version = 1)
public abstract class DatabaseAmericaCup extends RoomDatabase{

    public abstract FavoritiesDAO favoritiesDAO();
    public abstract GroupDAO grupoDAO();
    public abstract MatchDAO matchDAO();
    public abstract MatchStatisticsDAO matchStatisticsDAO();
    public abstract PlayerDAO playerDAO();
    public abstract TeamDAO teamDAO();
    public abstract TeamGroupDAO teamGroupDAO();
}
