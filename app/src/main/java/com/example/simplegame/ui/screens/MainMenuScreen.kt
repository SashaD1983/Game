package com.example.simplegame.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun MainMenuScreen(
    bestScore: Int?,
    onStartGame: () -> Unit,
    onHighScore: () -> Unit,
    onPrivacyPolicy: () -> Unit,
    onExit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Funny Combination", style = MaterialTheme.typography.headlineMedium)

        Text("Best result: ${bestScore ?: 0}")

        // Кнопки меню
        Button(onClick = onStartGame) { Text("Play") }
        Button(onClick = onHighScore) { Text("High Score") }
        Button(onClick = onPrivacyPolicy) { Text("Privacy Policy") }

        val context = LocalContext.current
        OutlinedButton(onClick = {
            (context as? Activity)?.finish()
            onExit()
        }) {
            Text("Exit")
        }
    }
}