package com.example.cuadernodedeku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cuadernodedeku.model.ProHero
import com.example.cuadernodedeku.model.proHeroes
import com.example.cuadernodedeku.ui.theme.DekuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DekuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DekuApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun DekuApp() {

    Scaffold( topBar = {
        DekuTopAppBar()
    }) { it ->

        val visibleState = remember {
            MutableTransitionState(false).apply {
                targetState = true
            }
        }

        AnimatedVisibility(
            visibleState = visibleState,
            enter = fadeIn(
                animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
            ),
            exit = fadeOut(),
            modifier = Modifier
        ) {
            LazyColumn(contentPadding = it) {
                itemsIndexed(proHeroes) { index, proHero ->
                    DekuCard(
                        proHero = proHero,
                        index = index + 1,
                        modifier = Modifier
                            .animateEnterExit(
                                enter = slideInHorizontally (
                                    animationSpec = spring(
                                        stiffness = StiffnessVeryLow,
                                        dampingRatio = DampingRatioLowBouncy
                                    ),
                                    initialOffsetX = { it * (index + 1 ) }
                                )
                            )
                    )
                }
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DekuTopAppBar(
    modifier : Modifier = Modifier
){
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier

            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun DekuCard(
    proHero: ProHero,
    index: Int,
    modifier: Modifier = Modifier
    ) {

    var expanded by remember { mutableStateOf(false) }

    Card (
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_medium), vertical = dimensionResource(R.dimen.padding_small))
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_small))
        ){
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "" + index + " " + stringResource(id = proHero.heroName),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = proHero.imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
            if(!expanded){
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
                }
            }
            if(expanded){
                Column {
                    Text(
                        text = "Nombre real: " + stringResource(id = proHero.name),
                        textDecoration = TextDecoration.Underline
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                    Text(
                        text = stringResource(id = proHero.desc),
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { expanded = false }) {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowUp,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LightPreview() {
    DekuTheme {
        DekuApp()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreview() {
    DekuTheme (useDarkTheme = true){
        DekuApp()
    }
}