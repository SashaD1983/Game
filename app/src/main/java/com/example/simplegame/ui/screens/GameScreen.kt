package com.example.simplegame.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simplegame.data.EmojiElement
import com.example.simplegame.ui.components.EmojiButtonPanel
import com.example.simplegame.ui.components.EmojiSequenceField
import com.example.simplegame.ui.components.EmojiShowcase


@Composable
fun GameScreen(
    onGameOver: (
        score: Int,
        targetSequence: List<EmojiElement>,
        playerSequence: List<EmojiElement>,
        isHighScore: Boolean
            ) -> Unit
) {
    var gameSequence by remember { mutableStateOf(listOf(EmojiElement.HEART)) }
    var playerSequence by remember { mutableStateOf(listOf<EmojiElement>()) }
    var isShowing by remember { mutableStateOf(true) }
    var level by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Game screen", style = MaterialTheme.typography.headlineSmall)

        if (isShowing) {
            EmojiShowcase(sequence = gameSequence) {
                isShowing = false
            }
        } else {
            EmojiSequenceField(sequence = playerSequence)

            EmojiButtonPanel { selectedEmoji ->
                val newSequence = playerSequence + selectedEmoji

                if (newSequence.size == gameSequence.size) {
                    val isCorrect = newSequence.zip(gameSequence).all { (p, g) -> p == g }

                    if (isCorrect) {
                        level++
                        gameSequence = gameSequence + EmojiElement.all.random()
                        playerSequence = emptyList()
                        isShowing = true
                    } else {
                        onGameOver(level, gameSequence, newSequence, level > 0)
                    }
                } else {
                    playerSequence = newSequence
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onGameOver(level, gameSequence, playerSequence, level > 0)
            }
        ) {
            Text("Finish the game")
        }
    }
}