package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.TitleDao
import com.example.data.database.dao.TopMoviesDao
import com.example.data.database.model.TitleEntity
import com.example.data.database.model.TopMoviesEntity

@Database(
    entities = [TopMoviesEntity::class, TitleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun topMoviesDao(): TopMoviesDao
    abstract fun titleDao(): TitleDao
}