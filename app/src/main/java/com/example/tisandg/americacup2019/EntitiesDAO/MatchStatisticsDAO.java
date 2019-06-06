package com.example.tisandg.americacup2019.EntitiesDAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tisandg.americacup2019.Entities.MatchStatistics;

import java.util.List;

@Dao
public interface MatchStatisticsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<MatchStatistics> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(MatchStatistics item);

    @Update
    public void update(MatchStatistics item);

    @Delete
    public void delete(MatchStatistics item);

    @Query("SELECT * FROM MatchStatistics ORDER BY match_id DESC")
    public List<MatchStatistics> loadAll();
}
