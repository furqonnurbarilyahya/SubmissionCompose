package com.bangkit.submissioncompose

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.submissioncompose.data.FootballPlayerRepository
import com.bangkit.submissioncompose.model.FootbalPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FootballPlayerViewModel (private val footballPlayerRepository: FootballPlayerRepository): ViewModel() {
    private val _groupedPlayers = MutableStateFlow(
        footballPlayerRepository.getPlayers()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedPlayers: StateFlow<Map<Char, List<FootbalPlayer>>> = _groupedPlayers

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedPlayers.value = footballPlayerRepository.searchPlayers(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}


class ViewModelFactory(private val footballPlayerRepository: FootballPlayerRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FootballPlayerViewModel::class.java)) {
            return FootballPlayerViewModel(footballPlayerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}