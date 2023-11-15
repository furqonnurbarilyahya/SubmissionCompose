package com.bangkit.submissioncompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.submissioncompose.data.FootballPlayerRepository
import com.bangkit.submissioncompose.model.FootbalPlayer
import com.bangkit.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (private val footballPlayerRepository: FootballPlayerRepository) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    private val _searchResult = MutableStateFlow<List<FootbalPlayer>>(emptyList())
    val searchResult: StateFlow<List<FootbalPlayer>> get() = _searchResult

    private val _uiState: MutableStateFlow<UiState<Map<Char, List<FootbalPlayer>>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Map<Char, List<FootbalPlayer>>>>
        get() = _uiState

    fun getAllPlayers() {
        viewModelScope.launch {
            footballPlayerRepository.groupSortedPLayers()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { player ->
                    _uiState.value = UiState.Success(player)
                }
        }
    }
    fun NewQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun searchPlayers() {
        val query = _query.value
        viewModelScope.launch {
            footballPlayerRepository.searchPlayers(query)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _searchResult.value = it
                }
        }
    }
}