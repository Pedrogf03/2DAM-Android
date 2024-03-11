package com.example.bookshelf.data

import com.example.bookshelf.ui.network.BookApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

interface AppContainer {
    val booksRepo: BookPhotosRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl =
        "https://www.googleapis.com/books/v1/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }

    override val booksRepo : BookPhotosRepository by lazy {
        NetworkBooksRepository(retrofitService)
    }

}