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

sealed interface FilteredGamesUiState {
    data class Success(val games: List<Game>) : FilteredGamesUiState
    object Error : FilteredGamesUiState
    object Loading : FilteredGamesUiState
}

class FilteredGamesViewModel(
    savedStateHandle: SavedStateHandle,
    private val gamesApiRepo: GamesApiRepo
) : ViewModel() {

    private val filterType: String = checkNotNull(savedStateHandle[FilteredGamesDestination.filterType])
    val filter: String = checkNotNull(savedStateHandle[FilteredGamesDestination.filter])
    var filteredGamesUiState: FilteredGamesUiState by mutableStateOf(FilteredGamesUiState.Loading)
        private set

    init {
        getGames()
    }

    fun getGames() {
        viewModelScope.launch {
            try {
                if(filterType.equals("cats")){
                    val listResult = gamesApiRepo.getGamesByGenre(filter)
                    filteredGamesUiState = FilteredGamesUiState.Success(listResult)
                } else if(filterType.equals("plats")){
                    if(filter.contains("PC") || filter.equals("Windows")) {
                        val listResult = gamesApiRepo.getGamesByPlatform("pc")
                        filteredGamesUiState = FilteredGamesUiState.Success(listResult)
                    } else if(filter.equals("Browser") || filter.equals("Web Browser")){
                        val listResult = gamesApiRepo.getGamesByPlatform("browser")
                        filteredGamesUiState = FilteredGamesUiState.Success(listResult)
                    }
                } else if(filterType.equals("gens")){
                    val listResult = gamesApiRepo.getGames()
                    val gameList: MutableList<Game> = mutableListOf()
                    for(game in listResult){
                        if(game.genero.equals(filter)) {
                            gameList.add(game)
                        }
                    }
                    filteredGamesUiState = FilteredGamesUiState.Success(gameList)
                }
            } catch (e: IOException) {
                filteredGamesUiState = FilteredGamesUiState.Error
            }
        }
    }

}