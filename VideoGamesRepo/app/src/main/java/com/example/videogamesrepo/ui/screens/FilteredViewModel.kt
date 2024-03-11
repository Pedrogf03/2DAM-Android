package com.example.videogamesrepo.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videogamesrepo.data.Categories
import com.example.videogamesrepo.data.GamesApiRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface FilteredScreenUiState {
    object Success : FilteredScreenUiState
    object Error : FilteredScreenUiState
    object Loading : FilteredScreenUiState
}

data class FilteredUiState (
    val filter: List<String> = listOf()
) : FilteredScreenUiState

class FilteredViewModel(
    savedStateHandle: SavedStateHandle,
    private val gamesApiRepo: GamesApiRepo
): ViewModel() {

    var filteredScreenUiState: FilteredScreenUiState by mutableStateOf(FilteredScreenUiState.Loading)
        private set

    val filterArg: String = checkNotNull(savedStateHandle[GameDetailsApiDestination.gameIdArg])

    private val _uiState = MutableStateFlow(FilteredUiState())
    val uiState: StateFlow<FilteredUiState> = _uiState.asStateFlow()

    init {
        getFilters()
    }

    fun getFilters() {
        viewModelScope.launch {
            try {
                val filterList: MutableList<String> = mutableListOf()
                if(filterArg.equals("cats")) {
                    filterList.addAll(Categories.cats)
                } else if(filterArg.equals("plats")) {
                    filterList.add("PC")
                    filterList.add("Browser")
                } else if(filterArg.equals("gens")) {
                    val games = gamesApiRepo.getGames()
                    for(game in games) {
                        if(!filterList.contains(game.genero)) {
                            filterList.add(game.genero)
                        }
                    }
                }
                filteredScreenUiState = FilteredScreenUiState.Success
                _uiState.update { currentState ->
                    currentState.copy(filter = filterList)
                }
            } catch (e: IOException) {
                filteredScreenUiState = FilteredScreenUiState.Error
            }
        }
    }

}