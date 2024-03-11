package com.example.bookshelf.ui.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "https://www.googleapis.com/books/v1/"

private val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface BookApiService {
    @GET("volumes?q=jazz+history")
    suspend fun getBooks(): Books
}
