package com.bangkit.submissioncompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.submissioncompose.data.PlayerRepository
import com.bangkit.submissioncompose.model.FootbalPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FootballPlayerViewModel (private val playerRepository: PlayerRepository): ViewModel() {
    private val _groupedPlayers = MutableStateFlow(
        playerRepository.getPlayers()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedPlayers: StateFlow<Map<Char, List<FootbalPlayer>>> = _groupedPlayers
}


class ViewModelFactory(private val playerRepository: PlayerRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FootballPlayerViewModel::class.java)) {
            return FootballPlayerViewModel(playerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}