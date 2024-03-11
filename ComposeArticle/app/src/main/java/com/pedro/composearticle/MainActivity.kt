package com.pedro.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pedro.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowText(stringResource(R.string.title), stringResource(R.string.p1), stringResource(R.string.p2))
                }
            }
        }
    }
}

@Composable
fun ShowText(title: String, p1: String, p2: String, modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier.fillMaxSize()
    )
    {
        Image(
            painter = painterResource(id = R.drawable.bg_compose_background),
            contentDescription = null
        )
        Text( // Title
            text = title,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
        )
        Text( // p1
            text = p1,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text( // p2
            text = p2,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ComposeArticleTheme {
        ShowText(stringResource(R.string.title), stringResource(R.string.p1), stringResource(R.string.p2))
    }
}