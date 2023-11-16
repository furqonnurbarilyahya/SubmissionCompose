package com.bangkit.submissioncompose.ui.screen.navigation

sealed class Screen (val route: String) {
    object Home: Screen("home")
    object About: Screen("about")
    object Favorite: Screen("favorite")
    object DetailPlayer: Screen("home/{playerId}") {
        fun createRoute(playerId: Long) = "home/$playerId"
    }
}