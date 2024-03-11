package com.example.videogamesrepo.data

import android.content.Context
import com.example.videogamesrepo.network.GamesApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {

    val gamesApiRepo: GamesApiRepo

    val gamesDbRepo: GamesDbRepo

}

class DefaultAppContainer(private val context: Context) : AppContainer {

    private val baseUrl =
        "https://www.freetogame.com/api/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: GamesApiService by lazy {
        retrofit.create(GamesApiService::class.java)
    }

    override val gamesApiRepo: GamesApiRepo by lazy {
        NetworkGamesRepo(retrofitService)
    }

    override val gamesDbRepo: GamesDbRepo by lazy {
        OfflineGamesRepo(GameDatabase.getDatabase(context).gameDao())
    }

}