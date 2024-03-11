package com.example.bookshelf.data

import com.example.bookshelf.ui.network.BookApiService
import com.example.bookshelf.ui.network.Books

interface BookPhotosRepository {
    suspend fun getBooks(): Books
}

class NetworkBooksRepository(private val bookApiService: BookApiService) : BookPhotosRepository {
    override suspend fun getBooks(): Books = bookApiService.getBooks()
}