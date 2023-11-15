package com.bangkit.submissioncompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.submissioncompose.data.FootballPlayerRepository
import com.bangkit.submissioncompose.model.DetailPlayer
import com.bangkit.submissioncompose.model.FootbalPlayer
import com.bangkit.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel (
    private val footballPlayerRepository: FootballPlayerRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<DetailPlayer>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<DetailPlayer>>>
        get() = _uiState

    fun getAllPlayers() {
        viewModelScope.launch {
            footballPlayerRepository.getAllPlayers()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { player ->
                    _uiState.value = UiState.Success(player)
                }
        }
    }
//    private val _groupedPlayers = MutableStateFlow(
//        footballPlayerRepository.getPlayers()
//            .sortedBy { it.name }
//            .groupBy { it.name[0] }
//    )
//    val groupedPlayers: StateFlow<Map<Char, List<FootbalPlayer>>> = _groupedPlayers
//
//    private val _query = mutableStateOf("")
//    val query: State<String> get() = _query
//
//    fun search(newQuery: String) {
//        _query.value = newQuery
//        _groupedPlayers.value = footballPlayerRepository.searchPlayers(_query.value)
//            .sortedBy { it.name }
//            .groupBy { it.name[0] }
//    }
}