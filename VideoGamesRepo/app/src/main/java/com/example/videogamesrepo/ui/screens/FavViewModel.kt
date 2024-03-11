package com.example.videogamesrepo.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videogamesrepo.data.Game
import com.example.videogamesrepo.data.GamesDbRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class FavViewModel(private val gamesDbRepo: GamesDbRepo) : ViewModel() {

    val favUiState: StateFlow<FavUiState> =
        gamesDbRepo.getAllGamesStream().map { FavUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class FavUiState(val gameList: List<Game> = listOf())