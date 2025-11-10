package com.example.simplegame.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simplegame.data.local.entity.HighScoreEntity

@Dao
interface HighScoreDao {

    @Insert
    suspend fun insert(score: HighScoreEntity)

    @Query("SELECT * FROM high_scores ORDER BY score DESC")
    suspend fun getAll(): List<HighScoreEntity>

    @Query("SELECT MAX(score) FROM high_scores")
    suspend fun getBestScore(): Int?
}
