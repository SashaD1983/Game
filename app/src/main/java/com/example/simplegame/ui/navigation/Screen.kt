package com.example.simplegame.ui.navigation

import com.example.simplegame.data.EmojiElement

sealed class Screen {
    object Splash : Screen()
    object MainMenu : Screen()
    object Game : Screen()
    data class GameOver(
        val score: Int,
        val isHighScore: Boolean,
        val targetSequence: List<EmojiElement>,
        val playerSequence: List<EmojiElement>
    ) : Screen()
    object HighScores : Screen()
    object PrivacyPolicy : Screen()
}