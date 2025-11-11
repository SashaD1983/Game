package com.example.simplegame.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.simplegame.data.EmojiElement
import com.example.simplegame.ui.components.EmojiSequenceField
import com.example.simplegame.ui.viewModel.GameViewModel


@Composable
fun GameOverScreen(
    score: Int,
    isHighScore: Boolean,
    targetSequence: List<EmojiElement>,
    playerSequence: List<EmojiElement>,
    viewModel: GameViewModel,
    onPlayAgain: () -> Unit,
    onMainMenu: () -> Unit
) {
    LaunchedEffect(score) {
        if (score > 0) {
            viewModel.saveScore(score)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Game Over", style = MaterialTheme.typography.headlineMedium)
        Text("Результат: $score")
        Text(if (isHighScore) "Новий рекорд!" else "Рекорд не побитий")

        Text("Загадана комбінація:")
        EmojiSequenceField(sequence = targetSequence)

        Text("Твоя відповідь:")
        ComparisonField(player = playerSequence)

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = onPlayAgain) { Text("Грати знову") }
            OutlinedButton(onClick = onMainMenu) { Text("Головне меню") }
        }
    }
}

@Composable
fun ComparisonField(
    player: List<EmojiElement>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        player.forEach { emoji ->
            Image(
                painter = painterResource(id = emoji.iconRes),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}


