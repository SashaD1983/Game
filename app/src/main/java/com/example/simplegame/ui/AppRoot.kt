package com.example.simplegame.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.simplegame.ui.screens.GameOverScreen
import com.example.simplegame.ui.screens.GameScreen
import com.example.simplegame.ui.screens.HighScoreScreen
import com.example.simplegame.ui.screens.MainMenuScreen
import com.example.simplegame.ui.screens.PrivacyPolicyScreen
import com.example.simplegame.ui.navigation.Screen
import com.example.simplegame.ui.screens.SplashScreen
import com.example.simplegame.ui.viewModel.GameViewModel

@Composable
fun AppRoot(viewModel: GameViewModel) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }

    when (val screen = currentScreen) {
        is Screen.Splash -> SplashScreen(
            onTimeout = { currentScreen = Screen.MainMenu }
        )
        is Screen.MainMenu -> MainMenuScreen(
            onStartGame = { currentScreen = Screen.Game },
            onHighScore = { currentScreen = Screen.HighScores },
            onPrivacyPolicy = { currentScreen = Screen.PrivacyPolicy },
            onExit = {},
            bestScore = viewModel.bestScore.collectAsState().value
        )
        is Screen.Game -> GameScreen(
            onGameOver = { score, target, player, isHigh ->
                currentScreen = Screen.GameOver(score, isHigh, target, player)
            }
        )
        is Screen.GameOver -> GameOverScreen(
            score = screen.score,
            isHighScore = screen.isHighScore,
            targetSequence = screen.targetSequence,
            playerSequence = screen.playerSequence,
            viewModel = viewModel,
            onPlayAgain = { currentScreen = Screen.Game },
            onMainMenu = { currentScreen = Screen.MainMenu }
        )
        is Screen.HighScores -> HighScoreScreen(
            viewModel = viewModel,
            onBack = { currentScreen = Screen.MainMenu }
        )
        is Screen.PrivacyPolicy -> PrivacyPolicyScreen(
            onBack = { currentScreen = Screen.MainMenu }
        )
    }
}