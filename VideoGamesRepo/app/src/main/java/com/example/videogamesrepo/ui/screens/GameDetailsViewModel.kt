package com.example.videogamesrepo.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videogamesrepo.data.Game
import com.example.videogamesrepo.data.GamesApiRepo
import com.example.videogamesrepo.data.GamesDbRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ApiGameUiState {
    data class Success(val game: Game) : ApiGameUiState
    object Error : ApiGameUiState
    object Loading : ApiGameUiState
}

class GameDetailsFromApiViewModel(
    savedStateHandle: SavedStateHandle,
    private val gamesApiRepo: GamesApiRepo
): ViewModel() {

    private val gameId: Int = checkNotNull(savedStateHandle[GameDetailsApiDestination.gameIdArg])

    var apiGameUiState: ApiGameUiState by mutableStateOf(ApiGameUiState.Loading)
        private set


    init {
        getGame()
    }

    fun getGame() {
        viewModelScope.launch {
            apiGameUiState = try {
                val listResult = gamesApiRepo.getGame(gameId)
                ApiGameUiState.Success(listResult)
            } catch (e: IOException) {
                ApiGameUiState.Error
            }
        }
    }

}

class GameDetailsFromDbViewModel(
    savedStateHandle: SavedStateHandle,
    private val gamesDbRepo: GamesDbRepo
): ViewModel() {

    private val gameId: Int = checkNotNull(savedStateHandle[GameDetailsDbDestination.gameIdArg])

    val dbUiState: StateFlow<DbGameUiState> =
        gamesDbRepo.getGameStream(gameId)
            .filterNotNull()
            .map {
                DbGameUiState(game = it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DbGameUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    suspend fun gameExists(id: Int): Boolean {
        return gamesDbRepo.getGameStream(id).first() != null
    }

    suspend fun saveGame(game: Game) {
        gamesDbRepo.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gamesDbRepo.deleteGame(game)
    }

}

data class DbGameUiState(
    val game: Game? = null
)