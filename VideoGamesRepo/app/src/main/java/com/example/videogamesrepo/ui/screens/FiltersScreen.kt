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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.videogamesrepo.R
import com.example.videogamesrepo.ui.GamesTopAppBar
import com.example.videogamesrepo.ui.navigation.NavigationDestination

object FilterDestination : NavigationDestination {
    override val route = "filter"
    override val titleRes = R.string.search_games
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    navigateToFilter: (String) -> Unit,
    navigateToSearch: (String) -> Unit,
    navigateToHome: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {


    Scaffold(
        topBar = {
            GamesTopAppBar(
                title = stringResource(FilterDestination.titleRes),
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

        FilterList(navigateToFilter, navigateToSearch, modifier.padding(innerPadding))

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FilterList(
    navigateToFilter: (String) -> Unit,
    navigateToSearch: (String) -> Unit,
    modifier: Modifier = Modifier
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

        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(50.dp, 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ){
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable(onClick = { navigateToFilter("cats") })
                    .animateEnterExit(
                        enter = slideInVertically(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessVeryLow,
                                dampingRatio = Spring.DampingRatioLowBouncy
                            ),
                            initialOffsetY = { it * 1 }
                        )
                    )
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Categories",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable(onClick = { navigateToFilter("gens") })
                    .animateEnterExit(
                        enter = slideInVertically(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessVeryLow,
                                dampingRatio = Spring.DampingRatioLowBouncy
                            ),
                            initialOffsetY = { it * 2 }
                        )
                    )
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Genres",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable(onClick = { navigateToFilter("plats") })
                    .animateEnterExit(
                        enter = slideInVertically(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessVeryLow,
                                dampingRatio = Spring.DampingRatioLowBouncy
                            ),
                            initialOffsetY = { it * 3 }
                        )
                    )
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Platforms",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable(onClick = { navigateToSearch("") })
                    .animateEnterExit(
                        enter = slideInVertically(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessVeryLow,
                                dampingRatio = Spring.DampingRatioLowBouncy
                            ),
                            initialOffsetY = { it * 4 }
                        )
                    )
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Search game",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

    }

}