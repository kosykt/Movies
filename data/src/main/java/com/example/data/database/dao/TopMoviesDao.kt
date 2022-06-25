package com.example.data.database.dao

import androidx.room.*
import com.example.data.database.model.TopMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(topMoviesEntity: List<TopMoviesEntity>)

    @Query("SELECT * FROM TopMoviesEntity")
    fun getAll(): Flow<List<TopMoviesEntity>>
}