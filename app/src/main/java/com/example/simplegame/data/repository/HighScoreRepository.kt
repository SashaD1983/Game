package com.example.simplegame.data.repository

import com.example.simplegame.data.local.dao.HighScoreDao
import com.example.simplegame.data.local.entity.HighScoreEntity

class HighScoreRepository(private val dao: HighScoreDao) {

    suspend fun insertScore(score: Int) {
        dao.insert(HighScoreEntity(score = score))
    }

    suspend fun getAllScores(): List<HighScoreEntity> = dao.getAll()

    suspend fun getBestScore(): Int? = dao.getBestScore()
}
