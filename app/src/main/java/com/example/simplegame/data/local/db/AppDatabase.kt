package com.example.simplegame.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simplegame.data.local.dao.HighScoreDao
import com.example.simplegame.data.local.entity.HighScoreEntity

@Database(entities = [HighScoreEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun highScoreDao(): HighScoreDao
}