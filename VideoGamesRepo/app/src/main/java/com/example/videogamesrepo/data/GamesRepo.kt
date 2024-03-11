package com.example.videogamesrepo.data

import com.example.videogamesrepo.network.GamesApiService
import kotlinx.coroutines.flow.Flow

interface GamesApiRepo {
    suspend fun getGames(): List<Game>

    suspend fun getGamesByGenre(genre: String): List<Game>

    suspend fun getGamesByPlatform(platform: String): List<Game>

    suspend fun getGame(id: Int): Game

}

class NetworkGamesRepo(private val gamesApiService: GamesApiService) : GamesApiRepo {
    override suspend fun getGames(): List<Game> = gamesApiService.getGames()

    override suspend fun getGamesByGenre(genre: String): List<Game> = gamesApiService.getGamesByGenre(genre)

    override suspend fun getGamesByPlatform(platform: String): List<Game> = gamesApiService.getGamesByPlatform(platform)

    override suspend fun getGame(id: Int): Game = gamesApiService.getGame(id)

}

interface GamesDbRepo {

    fun getAllGamesStream() : Flow<List<Game>>

    fun getGameStream(id: Int) : Flow<Game?>

    suspend fun insertGame(game: Game)

    suspend fun deleteGame(game: Game)

    suspend fun updateGame(game: Game)

}

class OfflineGamesRepo(private val gameDao: GameDAO) : GamesDbRepo {
    override fun getAllGamesStream(): Flow<List<Game>> = gameDao.getAllGames()

    override fun getGameStream(id: Int): Flow<Game?> = gameDao.getGame(id)

    override suspend fun insertGame(game: Game) = gameDao.insert(game)

    override suspend fun updateGame(game: Game) = gameDao.update(game)

    override suspend fun deleteGame(game: Game) = gameDao.delete(game)
}