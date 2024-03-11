package com.example.videogamesrepo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.videogamesrepo.R
import com.example.videogamesrepo.ui.AppViewModelProvider
import com.example.videogamesrepo.ui.GamesTopAppBar
import com.example.videogamesrepo.ui.navigation.NavigationDestination
import com.example.videogamesrepo.ui.utils.GamesListType

object SearchDestination : NavigationDestination {
    override val route = "search_destination"
    override val titleRes = R.string.search
    const val search = "search"
    val routeWithArgs = "${route}/{$search}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navigateToGame: (Int) -> Unit,
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    searchGame: (String) -> Unit,
    modifier: Modifier = Modifier,
    gamesListType: GamesListType,
    viewModel: SearchViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    Scaffold (
        topBar = {
            GamesTopAppBar(
                title = stringResource(HomeDestination.titleRes),
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

        var search by remember{ mutableStateOf("") }

        val searchUiState = viewModel.searchUiState
        val retryAction = viewModel::getGames

        Column (
            modifier = Modifier.padding(innerPadding)
        ){
            SearchEngine(
                value = search,
                onValueChange = { search = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    searchGame(search)
                }),
                searchGame = searchGame
            )

            when (searchUiState) {
                is SearchUiState.Loading -> LoadingScreen(modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding))
                is SearchUiState.Success -> {
                    if(searchUiState.games.isEmpty()) {
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ){
                            Text(text = stringResource(R.string.no_games_found))
                        }
                    } else {
                        PhotosGridScreen(games = searchUiState.games, navigateToGame = navigateToGame, gamesListType = gamesListType, modifier = modifier.fillMaxWidth())
                    }
                }
                else -> ErrorScreen(retryAction, modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding))
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchEngine(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    searchGame: (String) -> Unit,
    modifier: Modifier = Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.search_label)) },
        leadingIcon = {
            IconButton(onClick = { searchGame(value) }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = modifier.fillMaxWidth()
    )
}
