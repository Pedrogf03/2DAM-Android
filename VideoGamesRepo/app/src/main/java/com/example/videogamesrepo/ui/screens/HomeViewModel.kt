package com.example.videogamesrepo.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videogamesrepo.data.Game
import com.example.videogamesrepo.data.GamesApiRepo
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface HomeUiState {
    data class Success(val games: List<Game>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

class HomeViewModel(private val gamesApiRepo: GamesApiRepo) : ViewModel() {

    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getGames()
    }

    fun getGames() {
        viewModelScope.launch {
            try {
                val listResult = gamesApiRepo.getGames()
                homeUiState = HomeUiState.Success(listResult)
            } catch (e: IOException) {
                homeUiState = HomeUiState.Error
            }
        }
    }

}