package com.bangkit.submissioncompose.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bangkit.submissioncompose.R
import com.bangkit.submissioncompose.di.Injection
import com.bangkit.submissioncompose.ui.ViewModelFactory
import com.bangkit.submissioncompose.ui.common.UiState

@Composable
fun DetailPlayerScreen(
    playerId: Long,
    viewModel: DetailPlayerViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory(Injection.providedRepository())),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getPlayerById(playerId)
            }

            is UiState.Success -> {
                val data = it.data

                DetailPlayerContent(
                    photoUrl = data.dataPlayer.photoUrl,
                    playerName = data.dataPlayer.name,
                    desc = data.dataPlayer.desc,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
        }
    }

@Composable
fun DetailPlayerContent(
    photoUrl: String,
    playerName: String,
    desc: String,
    onBackClick: () -> Unit
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 10.dp)
            .verticalScroll(rememberScrollState())
    ){
        Box (modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
                    .align(Alignment.CenterStart)
                    .clickable { onBackClick() }
            )
        }
        Text(
            textAlign = TextAlign.Center,
            text = playerName,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(170.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 0.dp)) {
            Text(text = desc)
        }
    }
}