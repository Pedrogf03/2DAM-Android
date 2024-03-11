package com.example.valorantapp.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.valorantapp.R
import com.example.valorantapp.model.Arma

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ArmaValorantScreen(
    modifier: Modifier = Modifier,
    arma: Arma?,
    @DrawableRes image: Int
) {
    if (arma != null) {

        val visibleState = remember {
            MutableTransitionState(false).apply {
                targetState = true
            }
        }

        AnimatedVisibility(
            visibleState = visibleState,
            enter = fadeIn(
                animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
            ),
            exit = fadeOut(),
            modifier = Modifier
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 25.dp, vertical = 10.dp)
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {

                        ArmaCard(
                            arma = arma,
                            modifier = Modifier.animateEnterExit(
                                enter = slideInVertically(
                                    animationSpec = spring(
                                        stiffness = Spring.StiffnessVeryLow,
                                        dampingRatio = Spring.DampingRatioLowBouncy
                                    ),
                                    initialOffsetY = { it * 1 }
                                )
                            )
                        )

                    }
                }
            }
        }

    }
}

@Composable
fun ArmaCard(
    arma: Arma,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(arma.nombre),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 10.dp)
            )
            Image(
                painter = painterResource(id = arma.imagen),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .size(300.dp)
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = "// Precio",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.creds),
                        contentDescription = null,
                        modifier = Modifier.size(7.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = stringResource(arma.precio),
                        style = MaterialTheme.typography.displayMedium,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "// Descripcion",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(id = arma.desc),
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    }
}