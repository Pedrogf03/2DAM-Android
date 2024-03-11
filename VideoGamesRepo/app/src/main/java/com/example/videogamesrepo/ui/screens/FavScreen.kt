package com.example.videogamesrepo.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.videogamesrepo.R
import com.example.videogamesrepo.ui.AppViewModelProvider
import com.example.videogamesrepo.ui.GamesTopAppBar
import com.example.videogamesrepo.ui.navigation.NavigationDestination
import com.example.videogamesrepo.ui.utils.GamesListType

object FavDestination : NavigationDestination {
    override val route = "fav"
    override val titleRes = R.string.your_games
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavScreen(
    navigateToGame: (Int) -> Unit,
    navigateUp: () -> Unit,
    gamesListType: GamesListType,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val favUiState by viewModel.favUiState.collectAsState()

    Scaffold(
        topBar = {
            GamesTopAppBar(
                title = stringResource(FavDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateUp
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

        PhotosGridScreen(
            games = favUiState.gameList,
            navigateToGame = navigateToGame,
            gamesListType = gamesListType,
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding)
        )

    }
}