package com.poribarbazar;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.poribarbazar.model.ModelCart;

import java.util.List;

@Dao
public interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSingleData(ModelCart cartdb);

    @Update
    void updateSingleData(ModelCart cartdb);

    @Delete
    void DeleteSingleData(ModelCart cartdb);

    @Query("SELECT * FROM cartitem")
    LiveData<List<ModelCart>> getAllData();


}
