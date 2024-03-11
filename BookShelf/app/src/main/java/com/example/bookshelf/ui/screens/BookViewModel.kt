package com.example.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BooksPhotosApplication
import com.example.bookshelf.data.BookPhotosRepository
import com.example.bookshelf.data.NetworkBooksRepository
import com.example.bookshelf.ui.network.Book
import com.example.bookshelf.ui.network.Books
import com.example.bookshelf.ui.network.ImageLinks
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookUiState {
    data class Success(val photos: Books) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}

class BookViewModel(private val booksRepo: BookPhotosRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    init {
        getBookPhotos()
    }

    fun getBookPhotos() {
        viewModelScope.launch {
            try {
                bookUiState = BookUiState.Success(booksRepo.getBooks())
            } catch (e: IOException) {
                bookUiState = BookUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BooksPhotosApplication)
                val bookPhotosRepository = application.container.booksRepo
                BookViewModel(booksRepo = bookPhotosRepository)
            }
        }
    }

}