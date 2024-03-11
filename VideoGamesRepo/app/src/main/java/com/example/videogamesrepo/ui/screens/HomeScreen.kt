package com.example.videogamesrepo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.videogamesrepo.R
import com.example.videogamesrepo.data.Game
import com.example.videogamesrepo.ui.AppViewModelProvider
import com.example.videogamesrepo.ui.GamesTopAppBar
import com.example.videogamesrepo.ui.navigation.NavigationDestination
import com.example.videogamesrepo.ui.utils.GamesListType

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToGame: (Int) -> Unit,
    navigateToFavs: () -> Unit,
    navigateToFilter: () -> Unit,
    gamesListType: GamesListType,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    Scaffold (
        topBar = {
            GamesTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                navigateToFilter = navigateToFilter
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToFavs,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = ""
                )
            }
        }
    ) { innerPadding ->

        val homeUiState = viewModel.homeUiState
        val retryAction = viewModel::getGames

        when (homeUiState) {
            is HomeUiState.Loading -> LoadingScreen(modifier = modifier
                .fillMaxSize()
                .padding(innerPadding))
            is HomeUiState.Success -> PhotosGridScreen(games = homeUiState.games, navigateToGame = navigateToGame, gamesListType = gamesListType, modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding))
            else -> ErrorScreen(retryAction, modifier = modifier
                .fillMaxSize()
                .padding(innerPadding))
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PhotosGridScreen(
    games: List<Game>,
    navigateToGame: (Int) -> Unit,
    gamesListType: GamesListType,
    modifier: Modifier = Modifier
) {

    val dp: Dp

    when(gamesListType) {
        GamesListType.COMPACT -> dp = 150.dp
        else -> dp = 200.dp
    }

    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    var x by remember { mutableStateOf(1) }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = Modifier
    ) {

        LazyVerticalGrid(
            columns = GridCells.Adaptive(dp),
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(items = games) { game ->
                if(x == 14) { // Esta variable es para que al deslizar verticalmente sobre la pantalla de todos los juegos, la animacion no se vuelva exagerada. Cuando se muestran 14 juegos, el valor se reinicia.
                    x = 1
                }
                GameCard(
                    game = game,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * x }
                            )
                        ),
                    onGameClicked = navigateToGame
                )
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun GameCard(
    game: Game,
    modifier: Modifier = Modifier,
    onGameClicked: (Int) -> Unit
) {
    Card (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(game.img)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.games),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onGameClicked(game.id) })
        )
    }
}
