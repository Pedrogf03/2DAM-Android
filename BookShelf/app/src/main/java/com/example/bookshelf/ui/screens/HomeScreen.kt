package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.ui.network.Book
import com.example.bookshelf.ui.network.Books
import com.example.bookshelf.ui.theme.BookShelfTheme

@Composable
fun HomeScreen(
    bookUiState: BookUiState, retryAction: () -> Unit, modifier: Modifier = Modifier
) {
    when (bookUiState) {
        is BookUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is BookUiState.Success -> PhotosGridScreen(books = bookUiState.photos, modifier)
        is BookUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    BookShelfTheme {
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}


@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun BookPhotoCard(photo: Book, modifier: Modifier = Modifier) {

    if (photo.volumeInfo.imageLinks.thumbnail.startsWith("http://")) {
        photo.volumeInfo.imageLinks.thumbnail = photo.volumeInfo.imageLinks.thumbnail.replaceFirst("http://", "https://")
    }

    Card (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.volumeInfo.imageLinks.thumbnail)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.book_photo),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PhotosGridScreen(books: Books, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ){
        items(items = books.books, key = { book -> book.id }) {
            book -> BookPhotoCard(
                book,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(0.7f)
            )
        }
    }
}