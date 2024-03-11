package com.example.videogamesrepo.network

import com.example.videogamesrepo.data.Game
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApiService {
    @GET("games")
    suspend fun getGames(): List<Game>

    @GET("games")
    suspend fun getGamesByGenre(@Query("category") genre: String): List<Game>

    @GET("games")
    suspend fun getGamesByPlatform(@Query("platform") platform: String): List<Game>

    @GET("game")
    suspend fun getGame(@Query("id") id: Int): Game

}

