package com.example.bookshelf.ui.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Books(
    @SerialName(value="items")
    val books: List<Book>
)

@Serializable
data class Book(
    val id: String,
    val volumeInfo: Info
)

@Serializable
data class Info (
    val imageLinks: ImageLinks
)

@Serializable
data class ImageLinks(
    var thumbnail: String
)

