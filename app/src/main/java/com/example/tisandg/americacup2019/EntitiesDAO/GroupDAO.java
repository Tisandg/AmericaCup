package com.example.tisandg.americacup2019.EntitiesDAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tisandg.americacup2019.Entities.Group;

import java.util.List;

@Dao
public interface GroupDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<Group> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Group item);

    @Update
    public void update(Group item);

    @Delete
    public void delete(Group item);

    @Query("SELECT * FROM `Group` ORDER BY group_id DESC")
    public List<Group> loadAll();
}
