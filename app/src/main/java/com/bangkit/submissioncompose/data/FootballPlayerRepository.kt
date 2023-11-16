package com.bangkit.submissioncompose.data

import com.bangkit.submissioncompose.model.DetailPlayer
import com.bangkit.submissioncompose.model.FootbalPlayer
import com.bangkit.submissioncompose.model.FootballPlayersData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    fun getPlayerById(playerId: Long): DetailPlayer {
        return listPlayer.first {data ->
            data.dataPlayer.id == playerId
        }
    }

    fun groupSortedPLayers(): Flow<Map<Char, List<DetailPlayer>>> = flow {
        val sortedPlayers = listPlayer.sortedBy { it.dataPlayer.name }
        val groupedPlayers = sortedPlayers.groupBy { it.dataPlayer.name[0] }
        emit(groupedPlayers)
    }

    fun searchPlayers(query: String): Flow<List<DetailPlayer>> = flow {
          val filteredPlayers = listPlayer.filter {
              it.dataPlayer.name.contains(query, ignoreCase = true)
          }
            emit(filteredPlayers)
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