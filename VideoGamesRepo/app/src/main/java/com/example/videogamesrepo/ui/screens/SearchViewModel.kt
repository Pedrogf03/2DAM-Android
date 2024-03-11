package com.example.videogamesrepo.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videogamesrepo.data.Game
import com.example.videogamesrepo.data.GamesApiRepo
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface SearchUiState {
    data class Success(val games: List<Game>) : SearchUiState
    object Error : SearchUiState
    object Loading : SearchUiState
}

class SearchViewModel(
    savedStateHandle: SavedStateHandle,
    private val gamesApiRepo: GamesApiRepo
) : ViewModel() {

    val search: String? = savedStateHandle[SearchDestination.search]
    var searchUiState: SearchUiState by mutableStateOf(SearchUiState.Loading)
        private set

    init {
        getGames()
    }

    fun getGames() {
        viewModelScope.launch {
            try {
                val gameList = mutableListOf<Game>()
                if(search != null) {
                    val listResult = gamesApiRepo.getGames()
                    for(game in listResult) {
                        if(game.titulo.lowercase().contains(search.lowercase())) {
                            gameList.add(game)
                        }
                    }
                } else {
                    val listResult = gamesApiRepo.getGames()
                    gameList.addAll(listResult)
                }
                searchUiState = SearchUiState.Success(gameList)
            } catch (e: IOException) {
                searchUiState = SearchUiState.Error
            }
        }
    }

}