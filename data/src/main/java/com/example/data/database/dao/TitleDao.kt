package com.example.data.database.dao

import androidx.room.*
import com.example.data.database.model.TitleEntity

@Dao
interface TitleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(titleEntity: TitleEntity)

    @Query("SELECT * FROM TitleEntity WHERE id = :titleId")
    suspend fun getById(titleId: String): TitleEntity
}