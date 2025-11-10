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
    // Сохраняем результат в Room только если score > 0
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
        Text(if (isHighScore) "Новый рекорд!" else "Рекорд не побит")

        Text("Загаданная комбинация:")
        EmojiSequenceField(sequence = targetSequence)

        Text("Твой ответ:")
        ComparisonField(target = targetSequence, player = playerSequence)

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = onPlayAgain) { Text("Играть снова") }
            OutlinedButton(onClick = onMainMenu) { Text("Главное меню") }
        }
    }
}

// Компонент для сравнения двух последовательностей эмодзи
@Composable
fun ComparisonField(
    target: List<EmojiElement>,
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


