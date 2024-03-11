package com.example.videogamesrepo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.videogamesrepo.R
import com.example.videogamesrepo.ui.AppViewModelProvider
import com.example.videogamesrepo.ui.GamesTopAppBar
import com.example.videogamesrepo.ui.navigation.NavigationDestination


object FilteredDestination : NavigationDestination {
    override val route = "filtered_destination"
    override val titleRes = R.string.filters
    const val gameIdArg = "gameId"
    val routeWithArgs = "$route/{$gameIdArg}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilteredScreen(
    navigateBack: () -> Unit,
    navigateToFilteredGames: (String, String) -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FilteredViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    Scaffold (
        topBar = {
            GamesTopAppBar(
                title = stringResource(FilteredDestination.titleRes),
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

        val filterUiState by viewModel.uiState.collectAsState()
        val filteredScreenUiState = viewModel.filteredScreenUiState

        val retryAction = viewModel::getFilters

        when (filteredScreenUiState) {
            is FilteredScreenUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize().padding(innerPadding))
            is FilteredScreenUiState.Success -> FiltersListScreen(filter = filterUiState.filter, filterType = viewModel.filterArg, navigateToFilteredGames = navigateToFilteredGames, modifier = modifier.padding(innerPadding))
            else -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize().padding(innerPadding))
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FiltersListScreen(
    modifier: Modifier = Modifier,
    filter: List<String>,
    filterType: String,
    navigateToFilteredGames: (String, String) -> Unit
) {

    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = Modifier
    ) {

        LazyColumn (
            modifier = modifier
                .fillMaxSize()
                .padding(50.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            itemsIndexed(filter) {index, it ->
                CardFilter(
                    text = it,
                    filterType = filterType,
                    navigateToFilteredGames = navigateToFilteredGames,
                    modifier = Modifier.animateEnterExit(
                        enter = slideInVertically(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessVeryLow,
                                dampingRatio = Spring.DampingRatioLowBouncy
                            ),
                            initialOffsetY = { it * (index + 1) }
                        )
                    )
                )
            }
        }

    }

}

@Composable
fun CardFilter(
    text: String,
    filterType: String,
    navigateToFilteredGames: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(onClick = { navigateToFilteredGames(filterType, text) })
    ){
        Row (
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}