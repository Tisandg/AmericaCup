package com.example.tisandg.americacup2019.EntitiesDAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tisandg.americacup2019.Entities.Team;

import java.util.List;

@Dao
public interface TeamDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<Team> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Team item);

    @Update
    public void update(Team item);

    @Delete
    public void delete(Team item);

    @Query("SELECT * FROM Team ORDER BY team_id DESC")
    public List<Team> loadAll();

    @Query("SELECT * FROM Team WHERE group_id = :id")
    public List<Team> findByGroup(int id);
}
