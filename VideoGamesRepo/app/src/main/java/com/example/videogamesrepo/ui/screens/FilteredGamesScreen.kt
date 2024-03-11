package com.example.videogamesrepo.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.videogamesrepo.R
import com.example.videogamesrepo.ui.AppViewModelProvider
import com.example.videogamesrepo.ui.GamesTopAppBar
import com.example.videogamesrepo.ui.navigation.NavigationDestination
import com.example.videogamesrepo.ui.utils.GamesListType

object FilteredGamesDestination : NavigationDestination {
    override val route = "filteredGames_destination"
    override val titleRes = R.string.app_name
    const val filter = "gameId"
    const val filterType = "filterGame"
    val routeWithArgs = "$route/{$filterType}/{$filter}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilteredGamesScreen(
    navigateToGame: (Int) -> Unit,
    gamesListType: GamesListType,
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FilteredGamesViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    Scaffold(
        topBar = {
            GamesTopAppBar(
                title = viewModel.filter,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToHome,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = ""
                )
            }
        }
    ) { innerPadding ->

        val filteredGamesUiState = viewModel.filteredGamesUiState
        val retryAction = viewModel::getGames

        when (filteredGamesUiState) {
            is FilteredGamesUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize().padding(innerPadding))
            is FilteredGamesUiState.Success -> PhotosGridScreen(games = filteredGamesUiState.games, navigateToGame = navigateToGame, gamesListType = gamesListType, modifier = modifier.fillMaxWidth().padding(innerPadding))
            else -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize().padding(innerPadding))
        }

    }
}