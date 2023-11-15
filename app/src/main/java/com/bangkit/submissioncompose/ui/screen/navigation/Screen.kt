package com.bangkit.submissioncompose.ui.screen.navigation

sealed class Screen (val route: String) {
    object Home: Screen("home")
    object DetailPlayer: Screen("home/{playerId}") {
        fun createRoute(rewardId: Long) = "home/$rewardId"
    }
    object About: Screen("about")
    object Favorite: Screen("favorite")
}