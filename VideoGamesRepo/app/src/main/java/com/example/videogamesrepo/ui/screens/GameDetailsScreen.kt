package com.example.videogamesrepo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.videogamesrepo.R
import com.example.videogamesrepo.data.Game
import com.example.videogamesrepo.ui.AppViewModelProvider
import com.example.videogamesrepo.ui.GamesTopAppBar
import com.example.videogamesrepo.ui.navigation.NavigationDestination
import com.example.videogamesrepo.ui.utils.GameDetailsType
import kotlinx.coroutines.launch

object GameDetailsApiDestination : NavigationDestination {
    override val route = "game_details_api"
    override val titleRes = R.string.game_details
    const val gameIdArg = "gameId"
    val routeWithArgs = "$route/{$gameIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsFromApiScreen(
    navigateBack: () -> Unit,
    navigateToFilteredGames: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    apiViewModel: GameDetailsFromApiViewModel = viewModel(factory = AppViewModelProvider.Factory),
    dbViewModel: GameDetailsFromDbViewModel = viewModel(factory = AppViewModelProvider.Factory),
    detailsType: GameDetailsType
) {

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            GamesTopAppBar(
                title = stringResource(GameDetailsApiDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }, modifier = modifier
    ) { innerPadding ->

        val apiGameUiState = apiViewModel.apiGameUiState
        val retryAction = apiViewModel::getGame

        when (apiGameUiState) {
            is ApiGameUiState.Loading ->
                LoadingScreen(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            is ApiGameUiState.Success -> {
                var alreadyInDb by remember { mutableStateOf(false) }
                LaunchedEffect(apiGameUiState.game.id) {
                    alreadyInDb = dbViewModel.gameExists(apiGameUiState.game.id)
                }
                GameDetailsBody(
                    game = apiGameUiState.game,
                    alreadyInDb = alreadyInDb,
                    onSaveAction = { coroutineScope.launch{
                        dbViewModel.saveGame(apiGameUiState.game)
                    } },
                    onDeleteAction = { coroutineScope.launch{
                        dbViewModel.deleteGame(apiGameUiState.game)
                    } },
                    detailsType = detailsType,
                    navigateToFilteredGames = navigateToFilteredGames,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                )
            }
            else ->
                ErrorScreen(
                    retryAction = retryAction,
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
        }

    }
}

object GameDetailsDbDestination : NavigationDestination {
    override val route = "game_details_db"
    override val titleRes = R.string.game_details
    const val gameIdArg = "gameId"
    val routeWithArgs = "$route/{$gameIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsFromDbScreen(
    navigateBack: () -> Unit,
    navigateToFilteredGames: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GameDetailsFromDbViewModel = viewModel(factory = AppViewModelProvider.Factory),
    detailsType: GameDetailsType
) {

    val dbUiState = viewModel.dbUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    if(dbUiState.value.game != null) {

        val game = dbUiState.value.game!!

        Scaffold(
            topBar = {
                GamesTopAppBar(
                    title = stringResource(GameDetailsApiDestination.titleRes),
                    canNavigateBack = true,
                    navigateUp = navigateBack
                )
            }, modifier = modifier
        ) { innerPadding ->

            var alreadyInDb by remember { mutableStateOf(false) }
            LaunchedEffect(game.id) {
                alreadyInDb = viewModel.gameExists(game.id)
            }

            GameDetailsBody(
                game = game,
                alreadyInDb = alreadyInDb,
                onSaveAction = { coroutineScope.launch{
                    viewModel.saveGame(game)
                } },
                onDeleteAction = { coroutineScope.launch{
                    viewModel.deleteGame(game)
                } },
                detailsType = detailsType,
                navigateToFilteredGames = navigateToFilteredGames,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            )

        }
    }

}

@Composable
fun GameDetailsBody(
    game: Game,
    detailsType: GameDetailsType,
    modifier: Modifier = Modifier,
    onSaveAction: (Game) -> Unit,
    navigateToFilteredGames: (String, String) -> Unit,
    onDeleteAction: (Game) -> Unit,
    alreadyInDb: Boolean
) {

    if(detailsType == GameDetailsType.COMPACT) {
        CompactCard(
            game = game,
            onSaveAction = onSaveAction,
            navigateToFilteredGames = navigateToFilteredGames,
            onDeleteAction = onDeleteAction,
            alreadyInDb = alreadyInDb,
            modifier = modifier
        )
    } else if(detailsType == GameDetailsType.MEDIUM){
        CompactCard(
            game = game,
            onSaveAction = onSaveAction,
            navigateToFilteredGames = navigateToFilteredGames,
            onDeleteAction = onDeleteAction,
            alreadyInDb = alreadyInDb,
            modifier = modifier
        )
    } else if(detailsType == GameDetailsType.EXPANDED){
        ExpandedCard(
            game = game,
            onSaveAction = onSaveAction,
            navigateToFilteredGames = navigateToFilteredGames,
            onDeleteAction = onDeleteAction,
            alreadyInDb = alreadyInDb,
            modifier = modifier
        )
    } else {
        Text(text= "else", modifier = modifier)
    }

}

@Composable
fun CompactCard(
    game: Game,
    modifier: Modifier = Modifier,
    onSaveAction: (Game) -> Unit,
    navigateToFilteredGames: (String, String) -> Unit,
    onDeleteAction: (Game) -> Unit,
    alreadyInDb: Boolean
){

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp, 50.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(game.img)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.games),
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = game.titulo, style = MaterialTheme.typography.titleMedium, modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .fillMaxWidth(0.9f)
                )
                var isInDb by remember {mutableStateOf(alreadyInDb)}
                LaunchedEffect(alreadyInDb) {
                    isInDb = alreadyInDb
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        if (isInDb) {
                            onDeleteAction(game)
                        } else {
                            onSaveAction(game)
                        }
                        isInDb = !isInDb
                    }
                ) {
                    val icon = if (isInDb) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                append(stringResource(R.string.genre) + ": ")
                            }
                        },
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                append(game.genero)
                            }
                        },
                        textAlign = TextAlign.End,
                        modifier = Modifier.clickable(onClick = { navigateToFilteredGames("gens", game.genero) })
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                append(stringResource(R.string.platform) + ": ")
                            }
                        },
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                append(game.plataforma)
                            }
                        },
                        textAlign = TextAlign.End,
                        modifier = Modifier.clickable(onClick = { navigateToFilteredGames("plats", game.plataforma) })
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                append(stringResource(R.string.developer) + ": ")
                            }
                        },
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Spacer(Modifier.weight(1f))
                    Text(text = game.desarrollador, textAlign = TextAlign.End)
                }
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                append(stringResource(R.string.release_date) + ": ")
                            }
                        },
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Spacer(Modifier.weight(1f))
                    Text(text = game.formatDate(), textAlign = TextAlign.End)
                }
                Column (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                append(stringResource(R.string.short_description) + ": ")
                            }
                        },
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Text(text = game.desc, textAlign = TextAlign.Justify)
                }
            }
        }
    }
}

@Composable
fun ExpandedCard(
    game: Game,
    modifier: Modifier = Modifier,
    onSaveAction: (Game) -> Unit,
    navigateToFilteredGames: (String, String) -> Unit,
    onDeleteAction: (Game) -> Unit,
    alreadyInDb: Boolean
) {

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier
            .fillMaxSize()
            .padding(50.dp, 20.dp)
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
        ){
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(game.img)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(id = R.string.games),
                    error = painterResource(id = R.drawable.ic_broken_image),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    modifier = Modifier
                        .fillMaxHeight()
                )
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                    ){
                        Text(text = game.titulo, style = MaterialTheme.typography.titleLarge, modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp)
                            .fillMaxWidth(0.9f)
                        )
                        var isInDb by remember {mutableStateOf(alreadyInDb)}
                        LaunchedEffect(alreadyInDb) {
                            isInDb = alreadyInDb
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = {
                                if (isInDb) {
                                    onDeleteAction(game)
                                } else {
                                    onSaveAction(game)
                                }
                                isInDb = !isInDb
                            }
                        ) {
                            val icon = if (isInDb) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                    ){
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                    append(stringResource(R.string.genre) + ": ")
                                }
                            },
                            style = MaterialTheme.typography.labelMedium,
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                    append(game.genero)
                                }
                            },
                            textAlign = TextAlign.End,
                            modifier = Modifier.clickable(onClick = { navigateToFilteredGames("gens", game.genero) })
                        )
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                    ){
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                    append(stringResource(R.string.platform) + ": ")
                                }
                            },
                            style = MaterialTheme.typography.labelMedium,
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                    append(game.plataforma)
                                }
                            },
                            textAlign = TextAlign.End,
                            modifier = Modifier.clickable(onClick = { navigateToFilteredGames("plats", game.plataforma) })
                        )
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                    ){
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                    append(stringResource(R.string.developer) + ": ")
                                }
                            },
                            style = MaterialTheme.typography.labelMedium,
                        )
                        Spacer(Modifier.weight(1f))
                        Text(text = game.desarrollador, textAlign = TextAlign.End)
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                    ){
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                    append(stringResource(R.string.release_date) + ": ")
                                }
                            },
                            style = MaterialTheme.typography.labelMedium,
                        )
                        Spacer(Modifier.weight(1f))
                        Text(text = game.formatDate(), textAlign = TextAlign.End)
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp),
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                append(stringResource(R.string.short_description) + ": ")
                            }
                        },
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Text(text = game.desc, textAlign = TextAlign.Justify)
                }
            }
        }
    }
}