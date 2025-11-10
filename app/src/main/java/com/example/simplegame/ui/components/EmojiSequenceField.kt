package com.example.simplegame.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.simplegame.data.EmojiElement
import kotlinx.coroutines.delay

@Composable
fun EmojiSequenceField(
    sequence: List<EmojiElement>,
    onFinished: () -> Unit = {}
) {
    var visibleCount by remember { mutableStateOf(0) }

    // Запускаем анимацию при изменении sequence
    LaunchedEffect(sequence) {
        for (i in 1..sequence.size) {
            visibleCount = i
            delay(1000) // показываем на 1 секунду
        }
        onFinished()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        sequence.take(visibleCount).forEach { emoji ->
            Image(
                painter = painterResource(id = emoji.iconRes),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}