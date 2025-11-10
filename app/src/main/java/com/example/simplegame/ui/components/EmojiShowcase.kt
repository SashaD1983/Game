package com.example.simplegame.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
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
import kotlin.collections.forEach

@Composable
fun EmojiShowcase(
    sequence: List<EmojiElement>,
    onFinished: () -> Unit
) {
    var currentStep by remember { mutableStateOf(0) }

    LaunchedEffect(sequence) {
        currentStep = sequence.size
        delay(1000)

        currentStep = -1
        delay(1000)

        onFinished()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (sequence.isNotEmpty()) {
            if (currentStep == -1) {
                sequence.forEach { emoji ->
                    AnimatedVisibility(
                        visible = true,
                        enter = scaleIn(initialScale = 0.5f),
                        exit = fadeOut()
                    ) {
                        Image(
                            painter = painterResource(id = emoji.iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            } else {
                val emoji = sequence.last()
                AnimatedVisibility(
                    visible = true,
                    enter = scaleIn(
                        initialScale = 0.5f,
                        animationSpec = tween(durationMillis = 1000)
                    ),
                    exit = fadeOut()
                ) {
                    Image(
                        painter = painterResource(id = emoji.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}