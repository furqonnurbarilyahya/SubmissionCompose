package com.bangkit.submissioncompose.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.submissioncompose.di.Injection
import com.bangkit.submissioncompose.model.DetailPlayer
import com.bangkit.submissioncompose.ui.ViewModelFactory
import com.bangkit.submissioncompose.ui.common.UiState
import com.bangkit.submissioncompose.ui.components.PlayerListItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.providedRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllPlayers()
            }
            is UiState.Success -> {
                HomeContent(
                    detailPlayer = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
            else -> {}
        }
    }
}


@Composable
fun HomeContent (
    detailPlayer: List<DetailPlayer>,
    modifier: Modifier,
    navigateToDetail: (Long) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 80.dp),
        modifier = modifier.fillMaxSize().testTag("RewardList")
    ) {
        items(detailPlayer) {data ->
            PlayerListItem(
                name = data.dataPlayer.name,
                photoUrl = data.dataPlayer.photoUrl,
                modifier = Modifier.clickable {
                    navigateToDetail(data.dataPlayer.id)
                }
            )
        }
    }
}