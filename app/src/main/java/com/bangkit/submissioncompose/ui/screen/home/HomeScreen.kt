package com.bangkit.submissioncompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.submissioncompose.di.Injection
import com.bangkit.submissioncompose.model.DetailPlayer
import com.bangkit.submissioncompose.ui.ViewModelFactory
import com.bangkit.submissioncompose.ui.common.UiState
import com.bangkit.submissioncompose.ui.components.PlayerListItem
import com.bangkit.submissioncompose.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.providedRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {

    val query by viewModel.query.collectAsState(initial = "")
    val searchResult by viewModel.searchResult.collectAsState(initial = emptyList())

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllPlayers()
            }
            is UiState.Success -> {
                Column {
                    SearchBar(
                        query = query,
                        onQueryChange = {
                            viewModel.NewQuery(it)
                            viewModel.searchPlayers()
                        },
                        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                    )
                    HomeContent(
                        groupedPlayers = if (query.isEmpty()) uiState.data else emptyMap(),
                        listPlayer = searchResult,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                    )
                }
            }
            is UiState.Error -> {}
            else -> {}
        }
    }
}

@Composable
fun HomeContent (
    groupedPlayers: Map<Char, List<DetailPlayer>>,
    listPlayer: List<DetailPlayer>,
    modifier: Modifier,
    navigateToDetail: (Long) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 80.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("PlayersList")
    ) {
        if (listPlayer.isNotEmpty()) {
            items(listPlayer, key = { it.dataPlayer.id }) {
                PlayerListItem(
                    name = it.dataPlayer.name,
                    photoUrl = it.dataPlayer.photoUrl,
                    modifier = Modifier.clickable {
                        navigateToDetail(it.dataPlayer.id)
                    }
                )
            }
        } else {
            groupedPlayers.entries.forEach { (_, footballPlayer) ->
                items(footballPlayer) {
                    PlayerListItem(
                        name = it.dataPlayer.name,
                        photoUrl = it.dataPlayer.photoUrl,
                        modifier = Modifier.clickable {
                            navigateToDetail (it.dataPlayer.id)
                        }
                    )
                }
            }
        }
    }
}