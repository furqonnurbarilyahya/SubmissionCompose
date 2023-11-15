package com.bangkit.submissioncompose.data

import com.bangkit.submissioncompose.model.DetailPlayer
import com.bangkit.submissioncompose.model.FootbalPlayer
import com.bangkit.submissioncompose.model.FootballPlayersData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FootballPlayerRepository {
    private val listPlayer = mutableListOf<DetailPlayer>()

    init {
        if (listPlayer.isEmpty()) {
            FootballPlayersData.players.forEach {
                listPlayer.add(DetailPlayer(it, 0))
            }
        }
    }
    fun getAllPlayers(): Flow<List<DetailPlayer>> {
        return flowOf(listPlayer)
    }

    fun searchPlayers(query: String): List<FootbalPlayer> {
        return FootballPlayersData.players.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    companion object {
        @Volatile
        private var instance: FootballPlayerRepository? = null

        fun getInstance(): FootballPlayerRepository =
            instance ?: synchronized(this) {
                FootballPlayerRepository().apply {
                    instance = this
                }
            }
    }
}