package com.bangkit.submissioncompose.data

import com.bangkit.submissioncompose.model.FootbalPlayer
import com.bangkit.submissioncompose.model.FootballPlayersData

class PlayerRepository {
    fun getPlayers(): List<FootbalPlayer> {
        return FootballPlayersData.players
    }
}