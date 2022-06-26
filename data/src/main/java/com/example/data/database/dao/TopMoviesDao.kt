package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.model.TopMoviesEntity

@Dao
interface TopMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(topMoviesEntity: List<TopMoviesEntity>)

    @Query("SELECT * FROM TopMoviesEntity")
    suspend fun getAll(): List<TopMoviesEntity>
}