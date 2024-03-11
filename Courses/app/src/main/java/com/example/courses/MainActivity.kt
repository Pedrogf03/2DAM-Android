package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicGrid()
                }
            }
        }
    }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(DataSource.topics) {topic ->

            TopicCard(image = topic.image, text = topic.nombre, numCursos = topic.numCursos)
            
        }
    }
}

@Composable
fun TopicCard (
    @DrawableRes image: Int,
    @StringRes text: Int,
    numCursos: Int,
    modifier: Modifier = Modifier
) {
    
    Card {
        Row {
            Box {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = modifier
                        .size(68.dp, 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier.padding(16.dp, 16.dp, 16.dp, 8.dp)
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = numCursos.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoursesTheme {
        TopicGrid()
    }
}