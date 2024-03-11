package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    var currentStep by remember { mutableStateOf(0) }

    @DrawableRes var imageId by remember { mutableStateOf(R.drawable.volume_0) }
    @StringRes var title by remember { mutableStateOf(R.string.tomo0) }
    @StringRes var year by remember { mutableStateOf(R.string.tomo0_year) }

    when (currentStep) {
        0-> {
            imageId = R.drawable.volume_0
            title = R.string.tomo0
            year = R.string.tomo0_year
        }
        1-> {
            imageId = R.drawable.volume_1
            title = R.string.tomo1
            year = R.string.tomo1_year
        }
        2-> {
            imageId = R.drawable.volume_2
            title = R.string.tomo2
            year = R.string.tomo2_year
        }
        3-> {
            imageId = R.drawable.volume_3
            title = R.string.tomo3
            year = R.string.tomo3_year
        }
        4-> {
            imageId = R.drawable.volume_4
            title = R.string.tomo4
            year = R.string.tomo4_year
        }
        5-> {
            imageId = R.drawable.volume_5
            title = R.string.tomo5
            year = R.string.tomo5_year
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        PaintImage(imageId = imageId)
        Spacer(modifier = modifier.size(16.dp))
        PrintText(title = title, year = year)
        Spacer(modifier = modifier.size(25.dp))
        Row (
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Button(
                onClick = {
                    currentStep--
                    if(currentStep == -1) {
                        currentStep = 5
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.gray_900)
                )
            ) {
                Text(
                    text = "Previous",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.blue_300)
                )
            }
            Button(
                onClick = {
                    currentStep++
                    if(currentStep == 6) {
                        currentStep = 0
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue_300)
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.gray_900)
                )
            }
        }

    }

}

@Composable
fun PaintImage(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int
) {

    Surface (
        modifier = modifier
            .wrapContentSize()
            .shadow(32.dp)
            .background(MaterialTheme.colorScheme.background)
            .size(550.dp)
            .padding(32.dp)
    ){
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier.fillMaxSize()
        )
    }

}

@Composable
fun PrintText(
    @StringRes title: Int,
    @StringRes year: Int
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = colorResource(id = R.color.blue_100)
        )
        Text(
            text = stringResource(id = year),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = colorResource(id = R.color.gray_300)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Greeting()
    }
}