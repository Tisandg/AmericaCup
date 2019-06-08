package com.example.tisandg.americacup2019.EntitiesDAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tisandg.americacup2019.Entities.TeamGroup;

import java.util.List;

@Dao
public interface TeamGroupDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<TeamGroup> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(TeamGroup item);

    @Update
    public void update(TeamGroup item);

    @Delete
    public void delete(TeamGroup item);

    @Query("SELECT * FROM TeamGroup ORDER BY teamGroup_id DESC")
    public List<TeamGroup> loadAll();

    @Query("SELECT * FROM TeamGroup WHERE teamGroup_id = :id")
    public List<TeamGroup> findByGroup(int id);
}
