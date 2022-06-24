package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.model.TopMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopMoviesDao {

    @Insert
    suspend fun insert(topMoviesEntity: List<TopMoviesEntity>)

    @Delete
    suspend fun delete(topMoviesEntity: TopMoviesEntity)

    @Query("SELECT * FROM TopMoviesEntity")
    fun getAll(): Flow<List<TopMoviesEntity>>
}