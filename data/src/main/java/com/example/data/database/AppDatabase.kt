package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.TopMoviesDao
import com.example.data.database.model.TopMoviesEntity

@Database(
    entities = [TopMoviesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun topMoviesDao(): TopMoviesDao
}