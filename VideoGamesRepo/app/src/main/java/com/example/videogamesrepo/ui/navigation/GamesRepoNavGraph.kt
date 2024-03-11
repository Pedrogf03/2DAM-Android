package com.example.videogamesrepo.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.videogamesrepo.ui.screens.FavDestination
import com.example.videogamesrepo.ui.screens.FavScreen
import com.example.videogamesrepo.ui.screens.FilterDestination
import com.example.videogamesrepo.ui.screens.FilterScreen
import com.example.videogamesrepo.ui.screens.FilteredDestination
import com.example.videogamesrepo.ui.screens.FilteredGamesDestination
import com.example.videogamesrepo.ui.screens.FilteredGamesScreen
import com.example.videogamesrepo.ui.screens.FilteredScreen
import com.example.videogamesrepo.ui.screens.GameDetailsApiDestination
import com.example.videogamesrepo.ui.screens.GameDetailsDbDestination
import com.example.videogamesrepo.ui.screens.GameDetailsFromApiScreen
import com.example.videogamesrepo.ui.screens.GameDetailsFromDbScreen
import com.example.videogamesrepo.ui.screens.HomeDestination
import com.example.videogamesrepo.ui.screens.HomeScreen
import com.example.videogamesrepo.ui.screens.SearchDestination
import com.example.videogamesrepo.ui.screens.SearchScreen
import com.example.videogamesrepo.ui.utils.GameDetailsType
import com.example.videogamesrepo.ui.utils.GamesListType

@Composable
fun GamesRepoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    windowSize: WindowWidthSizeClass
) {

    val detailsType: GameDetailsType
    val gameListType: GamesListType

    when(windowSize) {
        WindowWidthSizeClass.Compact -> {
            detailsType = GameDetailsType.COMPACT
            gameListType = GamesListType.COMPACT
        }
        WindowWidthSizeClass.Medium -> {
            detailsType = GameDetailsType.MEDIUM
            gameListType = GamesListType.MEDIUM
        }
        WindowWidthSizeClass.Expanded -> {
            detailsType = GameDetailsType.EXPANDED
            gameListType = GamesListType.EXPANDED
        }
        else -> {
            detailsType = GameDetailsType.ELSE
            gameListType = GamesListType.ELSE
        }
    }

    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {

        // Pantalla home
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToGame = {
                    navController.navigate("${GameDetailsApiDestination.route}/${it}")
                },
                navigateToFavs = {
                    navController.navigate(FavDestination.route)
                },
                navigateToFilter = {
                    navController.navigate(FilterDestination.route)
                },
                gamesListType = gameListType
            )
        }

        // Pantalla de detalles de un juego sacado de la api
        composable(
            route = GameDetailsApiDestination.routeWithArgs,
            arguments = listOf(navArgument(GameDetailsApiDestination.gameIdArg) {
                type = NavType.IntType
            })
        ) {
            GameDetailsFromApiScreen(
                navigateBack = { navController.navigateUp() },
                navigateToFilteredGames = { filterType, filter -> navController.navigate("${FilteredGamesDestination.route}/${filterType}/${filter}") },
                detailsType = detailsType
            )
        }

        // Pantalla de favoritos
        composable(route = FavDestination.route) {
            FavScreen(
                navigateUp = { navController.navigateUp() },
                navigateToGame = {
                    navController.navigate("${GameDetailsDbDestination.route}/${it}")
                },
                navigateToHome = { navController.popBackStack(HomeDestination.route, inclusive = false) },
                gamesListType = gameListType
            )
        }

        // Pantalla de detalles de un juego sacado de la bbdd
        composable(
            route = GameDetailsDbDestination.routeWithArgs,
            arguments = listOf(navArgument(GameDetailsApiDestination.gameIdArg) {
                type = NavType.IntType
            })
        ) {
            GameDetailsFromDbScreen(
                navigateBack = { navController.navigateUp() },
                navigateToFilteredGames = { filterType, filter -> navController.navigate("${FilteredGamesDestination.route}/${filterType}/${filter}") },
                detailsType = detailsType
            )
        }

        // Pantalla que muestra los filtros disponibles
        composable(route = FilterDestination.route) {
            FilterScreen(
                navigateBack = { navController.navigateUp() },
                navigateToFilter = { navController.navigate("${FilteredDestination.route}/${it}")},
                navigateToSearch = { navController.navigate("${SearchDestination.route}/${it}") },
                navigateToHome = { navController.popBackStack(HomeDestination.route, inclusive = false) }
            )
        }

        // Pantalla que muestra los diferentes filtros entre los escogidos
        composable(
            route = FilteredDestination.routeWithArgs,
            arguments = listOf(navArgument(FilteredDestination.gameIdArg) {
                type = NavType.StringType
            })
        ) {
            FilteredScreen(
                navigateBack = { navController.navigateUp() },
                navigateToFilteredGames = { filterType, filter -> navController.navigate("${FilteredGamesDestination.route}/${filterType}/${filter}") },
                navigateToHome = { navController.popBackStack(HomeDestination.route, inclusive = false) }
            )
        }

        // Pantalla que muestra los juegos filtrados
        composable(
            route = FilteredGamesDestination.routeWithArgs,
            arguments = listOf(
                navArgument(FilteredGamesDestination.filterType) { type = NavType.StringType },
                navArgument(FilteredGamesDestination.filter) { type = NavType.StringType }
            )
        ) {
            FilteredGamesScreen(
                navigateToGame = {
                    navController.navigate("${GameDetailsApiDestination.route}/${it}")
                },
                navigateBack = { navController.navigateUp() },
                navigateToHome = { navController.popBackStack(HomeDestination.route, inclusive = false) },
                gamesListType = gameListType
            )
        }

        // Pantalla que muestra los juegos buscados por titulo cuando se le pasa un argumento
        composable(
            route = SearchDestination.routeWithArgs,
            arguments = listOf(
                navArgument(SearchDestination.search) { type = NavType.StringType },
            )
        ) {
            SearchScreen(
                navigateToGame = {
                    navController.navigate("${GameDetailsApiDestination.route}/${it}")
                },
                navigateBack = { navController.popBackStack(FilterDestination.route, inclusive = false) },
                searchGame = { navController.navigate("${SearchDestination.route}/${it}") },
                navigateToHome = { navController.popBackStack(HomeDestination.route, inclusive = false) },
                gamesListType = gameListType
            )
        }

        // Pantalla que muestra los juegos buscados por titulo cuando no se le pasa argumento (al entrar por primera vez o al hacer una busqueda en blanco)
        composable(route = SearchDestination.route + "/") {
            SearchScreen(
                navigateToGame = {
                    navController.navigate("${GameDetailsApiDestination.route}/${it}")
                },
                navigateBack = { navController.popBackStack(FilterDestination.route, inclusive = false) },
                searchGame = { navController.navigate("${SearchDestination.route}/${it}") },
                navigateToHome = { navController.popBackStack(HomeDestination.route, inclusive = false) },
                gamesListType = gameListType
            )
        }

    }
}