package com.bangkit.submissioncompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.submissioncompose.data.FootballPlayerRepository
import com.bangkit.submissioncompose.model.DetailPlayer
import com.bangkit.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailPlayerViewModel (private val footballPlayerRepository: FootballPlayerRepository): ViewModel(){
    private val _uiState: MutableStateFlow<UiState<DetailPlayer>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<DetailPlayer>> get() = _uiState

    fun getPlayerById(playerId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(footballPlayerRepository.getPlayerById(playerId))
        }
    }
}