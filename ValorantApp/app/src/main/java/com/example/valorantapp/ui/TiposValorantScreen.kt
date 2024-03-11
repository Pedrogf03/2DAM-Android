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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.valorantapp.model.Arma
import com.example.valorantapp.model.Armas

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TiposValorantScreen(
    modifier: Modifier = Modifier,
    onTiposButtonClicked: (List<Arma>) -> Unit,
    @DrawableRes image: Int
) {

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
                Column {
                    TiposCard(
                        onTiposButtonClicked = onTiposButtonClicked,
                        armas = Armas.ArmasDeMano,
                        option = "Armas de Mano",
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
                    TiposCard(
                        onTiposButtonClicked = onTiposButtonClicked,
                        armas = Armas.Subfusiles,
                        option = "Subfusiles",
                        modifier = Modifier.animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * 2 }
                            )
                        )
                    )
                    TiposCard(
                        onTiposButtonClicked = onTiposButtonClicked,
                        armas = Armas.Escopetas,
                        option = "Escopetas",
                        modifier = Modifier.animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * 3 }
                            )
                        )
                    )
                    TiposCard(
                        onTiposButtonClicked = onTiposButtonClicked,
                        armas = Armas.Rifles,
                        option = "Rifles",
                        modifier = Modifier.animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * 4 }
                            )
                        )
                    )
                    TiposCard(
                        onTiposButtonClicked = onTiposButtonClicked,
                        armas = Armas.Fusiles,
                        option = "Fusiles",
                        modifier = Modifier.animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * 5 }
                            )
                        )
                    )
                    TiposCard(
                        onTiposButtonClicked = onTiposButtonClicked,
                        armas = Armas.ArmasPesadas,
                        option = "Armas Pesadas",
                        modifier = Modifier.animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * 6 }
                            )
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TiposCard(
    modifier: Modifier = Modifier,
    onTiposButtonClicked: (List<Arma>) -> Unit,
    armas: List<Arma>,
    option: String
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = option,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 5.dp, start = 20.dp)
            )
            IconButton(
                onClick = { onTiposButtonClicked(armas) }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    }
}