package com.bangkit.submissioncompose

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.bangkit.submissioncompose.model.FootballPlayersData
import com.bangkit.submissioncompose.ui.screen.navigation.Screen
import com.bangkit.submissioncompose.ui.theme.SubmissionComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FootbalPlayersAppTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SubmissionComposeTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                FootballPlayersApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("PlayersList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FootballPlayersData.players[10].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailPlayer.route)
        composeTestRule.onNodeWithText(FootballPlayersData.players[10].name).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithText(text = "Favorit").performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText(text = "Profil").performClick()
        navController.assertCurrentRouteName(Screen.About.route)
    }

    @Test
    fun navhost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("PlayersList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FootballPlayersData.players[10].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailPlayer.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }
}