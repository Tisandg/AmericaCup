package com.example.tisandg.americacup2019.EntitiesDAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tisandg.americacup2019.Entities.Match;

import java.util.List;

@Dao
public interface MatchDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<Match> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Match item);

    @Update
    public void update(Match item);

    @Delete
    public void delete(Match item);

    @Query("SELECT * FROM Match ORDER BY match_date ASC")
    public List<Match> loadAll();

    @Query("SELECT * FROM Match WHERE match_id = :id")
    public Match findById(int id);

    @Query("SELECT * FROM Match WHERE match_id_teamA = :idTeam OR match_id_teamB = :idTeam")
    public List<Match> findByTeam(int idTeam);
}
