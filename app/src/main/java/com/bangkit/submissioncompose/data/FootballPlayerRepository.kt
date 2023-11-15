package com.bangkit.submissioncompose.data

import com.bangkit.submissioncompose.model.FootbalPlayer
import com.bangkit.submissioncompose.model.FootballPlayersData

class FootballPlayerRepository {
    fun getPlayers(): List<FootbalPlayer> {
        return FootballPlayersData.players
    }

    fun searchPlayers(query: String): List<FootbalPlayer> {
        return FootballPlayersData.players.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}