package com.example.videogamesrepo.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.videogamesrepo.GamesApplication
import com.example.videogamesrepo.ui.screens.FavViewModel
import com.example.videogamesrepo.ui.screens.FilteredGamesViewModel
import com.example.videogamesrepo.ui.screens.FilteredViewModel
import com.example.videogamesrepo.ui.screens.GameDetailsFromApiViewModel
import com.example.videogamesrepo.ui.screens.GameDetailsFromDbViewModel
import com.example.videogamesrepo.ui.screens.HomeViewModel
import com.example.videogamesrepo.ui.screens.SearchViewModel

object AppViewModelProvider {

    val Factory = viewModelFactory {

        initializer {
            val gamesApiRepo = gameApplication().container.gamesApiRepo
            HomeViewModel(gamesApiRepo = gamesApiRepo)
        }

        initializer {
            val gamesApiRepo = gameApplication().container.gamesApiRepo
            GameDetailsFromApiViewModel(
                this.createSavedStateHandle(),
                gamesApiRepo = gamesApiRepo
            )
        }

        initializer {
            FavViewModel(gameApplication().container.gamesDbRepo)
        }

        initializer {
            val gamesDbRepo = gameApplication().container.gamesDbRepo
            GameDetailsFromDbViewModel(
                this.createSavedStateHandle(),
                gamesDbRepo = gamesDbRepo
            )
        }

        initializer {
            FilteredViewModel(
                this.createSavedStateHandle(),
                gamesApiRepo = gameApplication().container.gamesApiRepo
            )
        }

        initializer {
            val gamesApiRepo = gameApplication().container.gamesApiRepo
            FilteredGamesViewModel(
                this.createSavedStateHandle(),
                gamesApiRepo = gamesApiRepo
            )
        }

        initializer {
            val gamesApiRepo = gameApplication().container.gamesApiRepo
            SearchViewModel(
                this.createSavedStateHandle(),
                gamesApiRepo = gamesApiRepo
            )
        }

    }

}

fun CreationExtras.gameApplication() : GamesApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as GamesApplication)