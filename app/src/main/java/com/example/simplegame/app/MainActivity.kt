package com.example.simplegame.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.simplegame.data.local.db.AppDatabase
import com.example.simplegame.data.repository.HighScoreRepository
import com.example.simplegame.ui.AppRoot
import com.example.simplegame.ui.viewModel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "game-database"
        ).build()

        val repository = HighScoreRepository(db.highScoreDao())
        val viewModel = GameViewModel(repository)

        setContent {
            AppRoot(viewModel)
        }
    }
}
