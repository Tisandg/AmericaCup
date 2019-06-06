package com.example.tisandg.americacup2019.EntitiesDAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tisandg.americacup2019.Entities.Player;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<Player> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Player item);

    @Update
    public void update(Player item);

    @Delete
    public void delete(Player item);

    @Query("SELECT * FROM Player ORDER BY player_id DESC")
    public List<Player> loadAll();
}
