package com.pedro.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pedro.presentation.ui.theme.PresentationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PresentationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PresentationApp()
                }
            }
        }
    }
}

@Composable
fun PresentationApp() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF4B88A2)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(0.75f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painterResource(id = R.drawable.img_20230808_174723_625),
                contentDescription = null,
                modifier = Modifier.size(125.dp)
            )
            Text(
                text = stringResource(R.string.name),
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD3D4D9)
            )
            Text(
                text = stringResource(R.string.description),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp),
                color = Color(0xFFD3D4D9)
            )
        }
        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(0.25f)
        ){
            Row (
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.phone_solid),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 20.dp)
                )
                Text(
                    text = stringResource(R.string.phone),
                    color = Color(0xFFD3D4D9),
                    fontWeight = FontWeight.Bold
                )
            }
            Row (
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.share_nodes_solid),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 20.dp)
                )
                Text(
                    text = stringResource(R.string.socials),
                    color = Color(0xFF0D3D4D9),
                    fontWeight = FontWeight.Bold
                )
            }
            Row (
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.envelope_solid),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 20.dp)
                )
                Text(
                    text = stringResource(R.string.email),
                    color = Color(0xFFD3D4D9),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PresentationTheme {
        PresentationApp()
    }
}