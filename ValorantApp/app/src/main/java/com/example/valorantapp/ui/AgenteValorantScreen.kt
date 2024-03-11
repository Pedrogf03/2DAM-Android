package com.example.valorantapp.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessLow
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.valorantapp.model.Agente
import com.example.valorantapp.model.Habilidad

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AgenteValorantScreen(
    modifier: Modifier = Modifier,
    agente: Agente?,
    @DrawableRes image: Int
) {
    if (agente != null) {

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
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
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
                    ) {
                        AgenteCard(
                            agente = agente,
                            modifier = Modifier.animateEnterExit(
                                enter = slideInVertically(
                                    animationSpec = spring(
                                        stiffness = Spring.StiffnessVeryLow,
                                        dampingRatio = DampingRatioLowBouncy
                                    ),
                                    initialOffsetY = { it * 1 }
                                )
                            )
                        )
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .animateEnterExit(
                                    enter = slideInVertically(
                                        animationSpec = spring(
                                            stiffness = Spring.StiffnessVeryLow,
                                            dampingRatio = DampingRatioLowBouncy
                                        ),
                                        initialOffsetY = { it * 1 }
                                    )
                                )
                        ) {
                            Text(
                                text = "// HABILIDADES",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                textAlign = TextAlign.Center
                            )
                            agente.habilidades.forEach {
                                HabilidadCard(
                                    habilidad = it
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AgenteCard(
    agente: Agente,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        modifier = modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(agente.nombre),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 10.dp)
            )
            Image(
                painter = painterResource(id = agente.imagen),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .size(300.dp)
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = "// Biografía",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(id = agente.bio),
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    }
}

@Composable
fun HabilidadCard(
    habilidad: Habilidad
) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = DampingRatioLowBouncy,
                    stiffness = StiffnessLow
                )
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = habilidad.imagen),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = habilidad.nombre),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    if (!expanded) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowUp,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
            if (expanded) {
                Text(
                    text = stringResource(id = habilidad.desc),
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(10.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}