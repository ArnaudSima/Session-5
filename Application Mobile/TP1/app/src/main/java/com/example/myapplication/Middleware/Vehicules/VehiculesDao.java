package com.example.myapplication.Middleware.Vehicules;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.concurrent.Flow;

@Dao
public interface VehiculesDao{
    @Insert
    fun insert()
}


