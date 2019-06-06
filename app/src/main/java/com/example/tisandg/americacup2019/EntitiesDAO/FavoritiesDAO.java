package com.example.tisandg.americacup2019.EntitiesDAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tisandg.americacup2019.Entities.Favorites;

import java.util.List;

@Dao
public interface FavoritiesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<Favorites> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Favorites item);

    @Update
    public void update(Favorites item);

    @Delete
    public void delete(Favorites item);

    @Query("SELECT * FROM Favorites ORDER BY favorites_id DESC")
    public List<Favorites> loadAll();
}