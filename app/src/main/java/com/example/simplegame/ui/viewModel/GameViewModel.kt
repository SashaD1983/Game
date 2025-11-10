package com.example.simplegame.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplegame.data.local.entity.HighScoreEntity
import com.example.simplegame.data.repository.HighScoreRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel(private val repository: HighScoreRepository) : ViewModel() {

    private val _bestScore = MutableStateFlow<Int?>(null)
    val bestScore: StateFlow<Int?> = _bestScore

    fun saveScore(score: Int) {
        viewModelScope.launch {
            repository.insertScore(score)
            _bestScore.value = repository.getBestScore()
        }
    }

    suspend fun getAllScores(): List<HighScoreEntity> {
        return repository.getAllScores()
    }
}
